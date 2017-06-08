# fortune-cookie-fulfillment-service
Spring Boot Application that provides a fulfillment service for processing fortune cookie orders.

## REST-Webservices

Use SoapUI or Google Postman for explicit call

1. `POST /fulfillment/shoppingCart`

Response (HTTP status code 200):

REST-Service for submitting a shopping cart. The REST-Service gets the following as input:

* ArrayList of cookie-objects with number of fortune cookies, price for a single fortune cookie (should actually not come from the client, but we might as well show this as a dangerous antipattern as well), and quote (because we don't use a DB)
* User Object with credit card number, address data containing name, address, zip code and city, E-Mail address of the user

Type: application/json

```json
{
	"type": "object",
	"properties": {
		"shoppingCartCookieResourceArrayList": {
			"type": "array",
			"items": {
				"type": "object",
				"properties": {
					"cookie": {
						"type": "object",
						"properties": {
							"price": {
								"type": "integer",
								"minimum": 0,
                "maximum": 1000,
                "exclusiveMinimum": true
							},
							"quote": {
								"type": "string"
							}
						},
						"required": ["price","quote"]
					},
					"amount": {
						"type": "integer",
						"minimum": 1
					}
				},
				"required": ["cookie", "amount"]
			}
		},
		"user": {
			"type": "object",
			"properties": {
				"name": {
					"type": "string"
				},
				"surname": {
					"type": "string"
				},
				"address": {
					"type": "string"
				},
				"zipCode": {
					"type": "string"
				},
				"city": {
					"type": "string"
				},
				"email": {
				    "type": "string"
				}
			},
			"required": ["name","surname","address","zipCode","city","email"]
		}
	}
}
```

Example:

```json
{
  "user": {
    "name": "cillum do quis",
    "surname": "in sit",
    "address": "mollit",
    "zipCode": "12345",
    "city": "nisi mollit in reprehenderit",
    "email": "mollit@nostrud.com"
  },
  "shoppingCartCookieResourceArrayList": [
    {
      "cookie": {
        "price": 431,
        "quote": "ipsum Lorem non sed labore"
      },
      "amount": 2
    },
    {
      "cookie": {
        "price": 181,
        "quote": "labore esse voluptate ad sint"
      },
      "amount": 4
    },
    {
      "cookie": {
        "price": 81,
        "quote": "eiusmod"
      },
      "amount": 1
    },
    {
      "cookie": {
        "price": 486,
        "quote": "pariatur dolore"
      },
      "amount": 4
    },
    {
      "cookie": {
        "price": 618,
        "quote": "voluptate fugiat amet"
      },
      "amount": 12
    }
  ]
}
```
