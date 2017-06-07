# fortune-cookie-product-service
Spring Boot Application that provides a service for managing products.

## REST-Webservices

Use SoapUI or Google Postman for explicit call

1. `GET /product/fortuneCookieList`

Response (HTTP status code 200):

List of fortune cookies. A Fortune Cookie consists of a string with the quote and a number containing the price in cents.

Type: application/json

```json
{
    "$schema": "http://json-schema.org/draft-04/schema#",
    "type": "array",
    "items": {
        "type": "object",
        "properties": {
            "price": {
                "type": "integer"
            },
            "quote": {
                "type": "string"
            }
        }
    }
}
```
Example:
```json
[
      {
      "quote": "Never quit!",
      "price": 422
   },
      {
      "quote": "It is now, and in this world, that we must live.",
      "price": 129
   },
      {
      "quote": "To be old and wise, you must first be young and stupid.",
      "price": 389
   }
]
```