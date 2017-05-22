package de.adesso.cookies.mailing;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private Random random = new Random();

	@PostMapping("/send")
	public ResponseEntity<Void> sendMail(@RequestBody @Validated MailResource mail) {
		try {
			feelingLucky();
			takeYourTime();
			logger.info("Mail sent successfully!");
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		catch (RuntimeException e) {
			logger.error("Cannot send mail due to error!", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void feelingLucky() {
		Double percentage = random.nextDouble();
		if (percentage < 0.3) {
			throw new RuntimeException("Ouch!!!");
		}
	}

	private void takeYourTime() {
		int milliseconds = random.nextInt(5000);
		try {
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e) {
			throw new RuntimeException("BWAAAAAH!", e);
		}

		if (milliseconds > 4500) {
			try {
				Thread.sleep(600000);
			}
			catch (InterruptedException e) {
				throw new RuntimeException("BOOOOH!", e);
			}
		}
	}

}
