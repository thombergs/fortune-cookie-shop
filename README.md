# Fortune Cookie Shop

This is a sample application that displays some of the problems we face once an application
runs in production, especially in a distributed architecture. Services are not available or 
they take their time answering requests or they produce errors. 

The application consists of an Angular frontend (module fortune-cookie-app) and a couple 
backend services implemented with Spring Boot.

## Running the application

To run the application, follow these steps:

1. run `gradlew fortune-cookie-app-server:bootrun`
1. run `gradlew fortune-cookie-fulfillment-service:bootrun`
1. run `gradlew fortune-cookie-mailing-service:bootrun`
1. run `gradlew fortune-cookie-product-service:bootrun`
1. run `gradlew fortune-cookie-edge-server:bootrun`
1. open http://localhost:8000 in your browser

## How the application works

Suffice to say that you can create quotes for fortune cookies and order those cookies with the
application. Finding out about the details, communication paths and pitfalls within the application is
part of the learning experience ;).