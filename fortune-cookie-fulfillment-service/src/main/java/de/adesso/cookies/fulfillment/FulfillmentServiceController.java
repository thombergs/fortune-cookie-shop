package de.adesso.cookies.fulfillment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Random;

@RestController
@RequestMapping("/fulfillment")
public class FulfillmentServiceController {

    @Autowired
    private FulfillmentServiceService fulfillmentService;

    private Logger logger = LoggerFactory.getLogger(FulfillmentServiceController.class);

    @RequestMapping(method = RequestMethod.POST, path = "/shoppingCart", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> shoppingCart(@RequestBody @Validated ShoppingCartResource submitShoppingCart) {

        try {

            fulfillmentService.sendMail(submitShoppingCart.getUser());

            logger.info("Shopping cart successfully submitted!");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            logger.error("Cannot submit shopping cart due to error!", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
