
package com.ttrung.supershop.order.service.impl;

import com.ttrung.supershop.order.domain.Order;
import com.ttrung.supershop.order.domain.OrderStatus;
import com.ttrung.supershop.order.dto.OrderDto;
import com.ttrung.supershop.order.dto.ProductOrderDto;
import com.ttrung.supershop.order.exception.PriceCalculationException;
import com.ttrung.supershop.order.mapper.OrderMapper;
import com.ttrung.supershop.order.repository.OrderRepository;
import com.ttrung.supershop.order.service.OrderService;
import com.ttrung.supershop.order.service.ShoppingCartService;
import com.ttrung.supershop.product.client.ProductService;
import com.ttrung.supershop.product.dto.PriceCalculationResult;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private ShoppingCartService shoppingCartService;
    private ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, ShoppingCartService shoppingCartService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @Override
    public OrderDto createOrder(String userId, OrderDto orderForm) {
        //TODO : Probably need to verify product existence, and other business logic, like if there is enough products in store.
        Order order = orderMapper.dtoToDomain(orderForm);
        order.setId(UUID.randomUUID().toString());
        order.setUserId(userId);
        order.setStatus(OrderStatus.NEW);
        order.setTotalPrice(calculateOrderPrice(orderForm.getProductOrders()));

        //TODO : Consider using transaction around persisting order and clearing cart operations.
        Order createdOrder = orderRepository.save(order);
        shoppingCartService.clearCart(userId);

        return orderMapper.domainToDto(createdOrder);
    }

    private Double calculateOrderPrice(List<ProductOrderDto> productOrders) {
        Call<PriceCalculationResult> priceCalcCall = productService.calculateTotalPrice(productOrders.stream()
                .map(productOrder -> new com.ttrung.supershop.product.dto.ProductOrderDto(productOrder.getProductId(), productOrder.getQuantity())).collect(Collectors.toList()));

        try {
            Response<PriceCalculationResult> response = priceCalcCall.execute();
            if (response.isSuccessful()) {
                return response.body().getTotalPrice();
            } else {
                throw new PriceCalculationException(String.format("The call to calculate total order price returns code [%s], message [%s]", response.code(), getErrMsg(response)));
            }
        } catch (IOException e) {
            throw new PriceCalculationException("Failed to calculate total order price", e);
        }
    }

    private static String getErrMsg(Response response) throws IOException {
        ResponseBody responseBody = response.errorBody();
        return responseBody != null ? responseBody.string() : "";
    }
}
