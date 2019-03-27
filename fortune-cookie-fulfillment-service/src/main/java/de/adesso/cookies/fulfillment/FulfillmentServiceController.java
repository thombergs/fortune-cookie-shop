package de.adesso.cookies.fulfillment;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
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

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

            runWithRetry(() ->
                    timedOut(() ->
                            fulfillmentService.sendMail(submitShoppingCart.getUser())
                    )
            );

      logger.info("Shopping cart successfully submitted!");
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (RuntimeException e) {
      logger.error("Cannot submit shopping cart due to error!", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } finally {
      timer.observeDuration();
    }
  }

    private void runWithRetry(Runnable function) {
        // Configure Retry strategy
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(5)
                .build();
        Retry retry = Retry.of("Void", retryConfig);

        // Decorate function with Retry
        Runnable decoratedFunction = Retry.decorateRunnable(retry, function);

        // Call decorated function
        decoratedFunction.run();
    }

    private Callable timedOut(Runnable function) {
        // Create Supplier<Future> from function
        Future futureSupplier = Executors.newSingleThreadExecutor().submit(function);

        // Configure time limiter
        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(4500))
                .cancelRunningFuture(true)
                .build();
        TimeLimiter timeLimiter = TimeLimiter.of(timeLimiterConfig);

        // Decorate future supplier with time limiter
        return TimeLimiter.decorateFutureSupplier(timeLimiter, () -> futureSupplier);
    }
}
