package de.adesso.cookies.mailing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MailingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailingServiceApplication.class, args);
	}
}
