package de.adesso.cookies.fulfillment;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fulfillment")
public class FulfillmentServiceController {

  @Autowired
  private FulfillmentServiceService fulfillmentService;

  private Logger logger = LoggerFactory.getLogger(FulfillmentServiceController.class);

  private Histogram timer;

  public FulfillmentServiceController() {
    this.timer = Histogram.build()
            .buckets(.01, .025, .05, .075, .1, .2, .3, .4, .5, .6, .7, .8, .9, 1, 1.5, 2, 2.5, 5, 7.5, 10)
            .name("http_request_duration")
            .labelNames("endpoint")
            .help("Histogram of HTTP request processing duration")
            .register();
  }

  @RequestMapping(method = RequestMethod.POST, path = "/shoppingCart", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Void> shoppingCart(
          @RequestBody @Validated ShoppingCartResource submitShoppingCart) {
    Histogram.Timer timer = this.timer
            .labels("/shoppingCart")
            .startTimer();
    try {

      fulfillmentService.sendMail(submitShoppingCart.getUser());

      logger.info("Shopping cart successfully submitted!");
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (RuntimeException e) {
      logger.error("Cannot submit shopping cart due to error!", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } finally {
      timer.observeDuration();
    }
  }


}
