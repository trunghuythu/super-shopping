# Super Shop Application
A sample shopping application built on top of Spring Boot, Spring Web, Spring OAuth2 and Spring Data. SSO is supported with Facebook as the authentication provider.

# How to run the app
Go to the root directory of the project
1. mvn clean package
2. docker-compose up

## Sign-in and get Access Token
Open your browser and access http://localhost:8127/signin, which will redirect you to Facebook login page. After you complete Facebook login, the system will create a corresponding user, and return an access token, used to access our protected APIs.

# Access Protected APIs
By default,our reverse proxy runs on port 8888 (you can configure the port in docker-compose.yaml file). So the base URI to access our APIs is _http://localhost:8888_

1. Products API:
    * Get Products
        * Endpoint: /products
        * ```curl -X GET \
        'http://localhost:8888/products?$filter=sam&$sort=name,asc&$page=1&$size=2' \
        -H 'Authorization: Bearer access-token'

2. Shopping Cart API:
    * Get Cart:
        * Endpoint: /shopping-carts
        * ```curl -X GET \
            http://localhost:8888/shopping-carts \
            -H 'Authorization: Bearer b822ba9f-727e-4e5e-9159-ea00bbfe6bf9'
    * Update Cart
        * Endpoint: /shopping-carts
        * ```curl -X PUT \
            http://localhost:8888/shopping-carts \
            -H 'Authorization: Bearer b822ba9f-727e-4e5e-9159-ea00bbfe6bf9' \
            -H 'Content-Type: application/json' \
            -d '{
          	"productOrders": [
          		{
          			"productId": "product-id",
          			"quantity": 3
          		}
          	]
          }'
          
3. Order API;
    * Create Order:
        * Endpoint: /orders
        * ```curl -X POST \
            http://localhost:8888/orders \
            -H 'Authorization: Bearer b822ba9f-727e-4e5e-9159-ea00bbfe6bf9' \
            -H 'Content-Type: application/json' \
            -d '{
              "productOrders": [
                  {
                      "productId": "product-id",
                      "quantity": 1
                  }
              ],
              "deliveryAddress": "38 Huynh Lan Khanh"
          }'

## Limitation & Road-map
	. Requests should be authenticated at the gateway, and enriched with authentication details (id, email, role) before they get to backing services. Once a request get to our services, there should be enough information about the principal who makes the request, without the services go asking for authenticatio ndetails. Also, this helps to bypass authentication in internal process communication between internal services.
	. Implement consistent error handling model:
		. Support i18n
		. Generate error ID to help tracking issues.
	. Apply OData to provide unified mechanism to produce and consume APIs