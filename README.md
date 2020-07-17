# Super Shop Application
A sample shopping application built on top of Spring Boot, Spring Web, Spring OAuth2 and Spring Data. SSO is supported with Facebook as the authentication provider.

## Infrastructure Overview
The application is built around 3 microservices:
1. urp-service: Responsible for user sign-in process, as well as authentication and authorization.
2. product-service: Manage products, support auditing
3. order-service: Manage user's shopping carts and orders

You can see the corresponding Maven modules of the services in the project.

## Security
The system uses OpenID Connect on top of OAuth2 for authentication and authorization. Below is the sequence diagram describes the sign-in process of user using Facebook as OpenId Provider.

![image info](./Signin-Sequence-Diagram.png)

Below diagram shows the flow of user requesting protected resources on our backing services(product service and order service in this case)
![image info](./Requesting-Resource-Diagram.png)


## Entity Relationship Diagram
![image info](./ER-Diagram.png)


# How to run the app
Go to the root directory of the project
1. ```mvn clean package```
2. ```docker-compose up```

On application startup, we already insert sample products, so you should be able to consume the API with minimum effort.

## Sign-in and get Access Token
Open your browser and access http://localhost:8127/signin, which will redirect you to Facebook login page. After you complete Facebook login, the system will create a corresponding user, and return an access token, used to access our protected APIs.

# API Access
By default,our reverse proxy runs on port 8888 (you can configure the port in docker-compose.yaml file). So the base URI to access our APIs is _http://localhost:8888_

1. Products API:
    * Get Products
        * Endpoint: /products
        * ```
          curl -X GET \
             'http://localhost:8888/products?$filter=sam&$sort=name,asc&$page=1&$size=10' \
             -H 'Authorization: Bearer 53334159-9765-44f3-ae7c-4d548280a785'
          ```
    * Create Products
        * Endpoint: /products
        * ```
          curl -X POST \
               http://localhost:8888/products \
               -H 'Authorization: Bearer 53334159-9765-44f3-ae7c-4d548280a785' \
               -H 'Content-Type: application/json' \
               -d '{
                     "name": "Super MacT9",
                     "category": "Digital Devices",
                     "brand": "Apple",
                     "price": 5000000
             }'
          ```
    * Update Products
        * Endpoint: /products
        * ```
          curl -X PUT \
               http://localhost:8888/products/product-id \
               -H 'Authorization: Bearer 53334159-9765-44f3-ae7c-4d548280a785' \
               -H 'Content-Type: application/json' \
               -d '{
                         "name": "Samsung TV 4k",
                         "category":"Digital Devices",
                         "brand": "Samsung",
                         "price": 18000000
                     }'
          ```

2. Shopping Cart API:
    * Get Cart:
        * Endpoint: /shopping-carts
        * ```
          curl -X GET \
            http://localhost:8888/shopping-carts \
            -H 'Authorization: Bearer 53334159-9765-44f3-ae7c-4d548280a785'
          ```
    * Update Cart
        * Endpoint: /shopping-carts
        * ```
          curl -X PUT \
            http://localhost:8888/shopping-carts \
            -H 'Authorization: Bearer 53334159-9765-44f3-ae7c-4d548280a785' \
            -H 'Content-Type: application/json' \
            -d '{
          	"productOrders": [
          		{
          			"productId": "product-id",
          			"quantity": 3
          		}
          	]
          }'
          ```
3. Order API;
    * Create Order:
        * Endpoint: /orders
        * ```
          curl -X POST \
            http://localhost:8888/orders \
            -H 'Authorization: Bearer 53334159-9765-44f3-ae7c-4d548280a785' \
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
          ```
4. Product Audit API;
    * Get Changes Audit:
        * Endpoint: /products/audit
        * ```
          curl -X GET \
            http://localhost:8888/products/audit \
            -H 'Authorization: Bearer access-token' \
            -H 'Content-Type: application/json'
          ```


## Limitation & Road-map
	1. Requests should be authenticated at the gateway, and enriched with authentication details (id, email, role) before they get to backing services.
	 Once a request get to our backing services, there should be enough information about the principal who makes the request, without the services go asking for authentication details.
	 Also, this helps to bypass authentication in internal process communication between internal services and facilitate testing on individual services.
	 
	2. Implement consistent error handling model:
		. Support i18n
		. Generate error ID to help tracking issues.
		
	3. Apply OData to provide unified mechanism to produce and consume APIs