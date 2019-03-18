package de.adesso.cookies.mailing;

import io.prometheus.client.Histogram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationProperties("fortune-cookies")
public class MailingController {

  private Logger logger = LoggerFactory.getLogger(MailingController.class);

  private Histogram timer;

  MailService mailService = new MailService();

  public MailingController() {
    this.timer = Histogram.build()
            .buckets(.01, .025, .05, .075, .1, .2, .3, .4, .5, .6, .7, .8, .9, 1, 1.5, 2, 2.5, 5, 7.5, 10)
            .name("http_request_duration")
            .labelNames("endpoint")
            .help("Histogram of HTTP request processing duration")
            .register();
  }

  @PostMapping("/send")
  public ResponseEntity<Void> sendMail(@RequestBody @Validated MailResource mail) {
    Histogram.Timer timer = this.timer
            .labels("/send")
            .startTimer();
    try {

      mailService.sendMail(mail);
      return new ResponseEntity<>(HttpStatus.CREATED);

    } catch (RuntimeException e) {
      logger.error("Cannot send mail due to error!", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } finally {
      timer.observeDuration();
    }
  }

}
