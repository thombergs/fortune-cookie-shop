package de.adesso.cookies.fulfillment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class FulfillmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FulfillmentServiceApplication.class, args);
	}
}
