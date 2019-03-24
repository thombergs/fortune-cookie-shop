package de.adesso.cookies.mailing;

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

	@PostMapping("/send")
	public ResponseEntity<Void> sendMail(@RequestBody @Validated MailResource mail) {
		try {

			new MailService(mail).execute();
			return new ResponseEntity<>(HttpStatus.CREATED);

		}  catch (RuntimeException e) {
			logger.error("Cannot send mail due to error!", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
