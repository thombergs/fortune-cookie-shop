package de.adesso.cookies.fulfillment;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

  private Timer timer;

  public FulfillmentServiceController(MetricRegistry metricRegistry) {
    this.timer = metricRegistry.timer(FulfillmentServiceController.class.getSimpleName());
  }

  @RequestMapping(method = RequestMethod.POST, path = "/shoppingCart", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Void> shoppingCart(
      @RequestBody @Validated ShoppingCartResource submitShoppingCart) {
    Timer.Context context = timer.time();
    try {

      fulfillmentService.sendMail(submitShoppingCart.getUser());

      logger.info("Shopping cart successfully submitted!");
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (RuntimeException e) {
      logger.error("Cannot submit shopping cart due to error!", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } finally {
      context.stop();
    }
  }


}
