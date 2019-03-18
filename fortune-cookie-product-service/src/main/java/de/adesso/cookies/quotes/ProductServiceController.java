package de.adesso.cookies.quotes;

import io.prometheus.client.Histogram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/product")
public class ProductServiceController {

  private Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

  @Autowired
  private FortuneCookieService fortuneCookieService;

  private Histogram timer;

  public ProductServiceController() {
    this.timer = Histogram.build()
            .buckets(.01, .025, .05, .075, .1, .2, .3, .4, .5, .6, .7, .8, .9, 1, 1.5, 2, 2.5, 5, 7.5, 10)
            .name("http_request_duration")
            .labelNames("endpoint")
            .help("Histogram of HTTP request processing duration")
            .register();
  }

  @RequestMapping(method = RequestMethod.GET, path = "/fortuneCookieList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ArrayList<FortuneCookieResource> getFortuneCookieList() {
    Histogram.Timer timer = this.timer
            .labels("/fortuneCookieList")
            .startTimer();
    try {
      ArrayList<FortuneCookieResource> cookieList = fortuneCookieService.getCookies();

      logger.info("FortuneCookies sent successfully!");
      return cookieList;
    } finally {
      timer.observeDuration();
    }
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleException(final RuntimeException e) {
    logger.error("Cannot sent FortuneCookieDao due to error!", e);
    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}

