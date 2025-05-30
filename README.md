In this Project I implemented Spring Cloud concepts like Microservice Registration & Discovery with Netflix Eureka, API Gateway and Resiliance4j(Circuit Breaker Pattern). 
Created three service User_Services, Post_Service and Notification_Service

Netflix - Eureka(Service Registration and Discovery)
The user service is trying to communate and get the data from Post service and Notification service
Initially I tried to establish communication between microservices using REST template. In REST template we used .getForOject("URL", Response Type) method.
This method is not very effective when we have dynamic URL's and when the URL's/Port of the services keeps changing, then we have to keep changing the URL manually.
Hence to over come this we try to use the Netflix - Eureka, to register our serveice as Client's into the Eureka Server and we provide a name to the service while registering into the Server.
We need to create another service in spring intializer as Eureka Server add the dependencies.
Initially when running the eureka server it registers it self also as a service, so we need to mention in the application properties to register as service as False.
We need to add the dependencies and dependency management in the Eureka Server and also 3 services.
We can use the name of the service instead of mentioning the port of the servie we want to communicate with
Example: "http://localhost:8081/Post/1" we can mention in the URL as ""http://post-service/post/1.

API Gateway:
When the application would scale and there can be large number instances of these services and eventually there can be more services not limited to the above 3 services.
So whenever a 3rd party application wants to talk to these services they need to have exact url's, so it would be difficult to communicate.
So API gateways will have all the services in it and the gateway is exposed to any external application. So any client side application will use the API gateway URL and send the request to API gateway,
the gateway will internally do the routing mechanism basis on the request to the respective service.
We need to add dependencies  Gateway(spring cloud routing) and Eureka discovery client(API gateway registers itself with eureka server).
We also need to annotate the main class with @EnableDiscoveryClient
We need to mention the routing part in the application.properties of the API-Gateway service.
Now when all the Services are up and running we can communicate with the user service through API gateway(server.port = 9000. Port given for API gateway.)
we can directly call as http://localhost:9000/user/1 instead of calling then individually.

Resliance4j(Circuitbreaker pattern):
Resiliance4j is lightweight fault tolerance library designed for functional programming.
Circuit Breaker Pattern : This is used to handel the communication between services.If any service during communications is not able to respond for any reason or the service has some issures, the requesting service
will not get any response. In such scenarios the circuit breaker comes into picture. Whenever a service is not able to respond it identifies the service and does some default processing or fallback mechanism and 
return some response to the request.
Circuit breaker prevents the cascading failure of the entire system when one service-instance is experiencing some issue. It identfies the service that is not running and will excute the fallback method instead.

Circuit Breaker State Machine:
Intially the Service state is Closed.
Whenever there is an issue with the service and there are lot of failures in response, then when the failure rate crosses the threshold value then the Service state changes from closed to Open.
There is a wait-duration mentioned for the service, after the wait the service state changes to half_open and checks again with the responses, if the failure rate is less than the threshold value given the service state
is changed back to CLOSED otherwise OPEN.
In the project above we need to mention the dependencies in POM.XML file of the user service as the other 2 are trying to communicate with it. If the other 2 are comminicating seperately then we need to provide these 
dependencies as well.
Dependencies: AOP dependency , Resilliance4J dependency and Actuator Dependency.
In Application.properties file or application.yaml file we can metion the details of resiliance4j circuit breaker mechanism like threshold value, failure rate and all Health indicator properties.
In User Service we need to define the service name 
In user_service we need to mentuon the annotation near GETMETHOD with @CircuitBreaker(name = USER_SERVICE, fallbackMethod = "userServiceFallBack").
We need to define the userServiceFallBack method seperately. This will get executed whenever any of the service is not responding. So if the post service and notification service is not responding the fallback method wiil
be returned. 
We can check the health of the service by visiting : localhost:8080/actuatore/health

Source: Youtube video by ThinkConstructive.
