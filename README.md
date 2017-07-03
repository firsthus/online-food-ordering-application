Microservice design, architecture and API design can be found in design_updated.pdf. I have changed some design from last design.pdf, mainly realized the MQ should be used between input payment and validate payment, not between validate and output paid order.

In short, 6 microservices are used,

1. restaurant service: provides CRUD operation on restaurant and food_item(menu) information
2. order service: provides generation of order, search order based on restaurant, and view confirmed order if payment is accepted.
3. payment distributor service: receives the input payment information from payment UI and provides source of payment validator
4. payment validator suervice: consumer of distributor, validate received payment info, then return validation status to order service 
5. eureka: used for service registration and discovery
6. hystrix: used for circuit breaker, mainly for fall back method of payment-validator service

mySQL is used for data persistency for order service and restaurant service.
rabbitMQ is used to decouple and asynchronize the payment input and validation.
Both of them are launched using docker configured in docker-compose.yml

The test cases include: unit test and integration tests and MVC tests for order service, restaurant service and validation service.

There are 4 UI designed, unfortuanetely I am not able to implement it yet. Those UI are:
1. UI of view menu based on restaurant ( corresponds to back-end service in restaurant-service)
2. UI of select item from menu (corresponds to back-end service in restaurant-service and order-service)
3. UI of input payment infomation (corresponds to back-end service in payment distributor-service)
4. UI of view the paid order information if payment is accepted. (correponds to back-end service in order-service)
