package de.adesso.cookies.fulfillment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Random;
import java.util.function.Supplier;

@Service
public class FulfillmentServiceService {

    private Logger logger = LoggerFactory.getLogger(FulfillmentServiceController.class);

    private Random random = new Random();

    public void sendMail(UserResource userResource) {

        try {

            feelingLucky();
            takeYourTime();

            //generate mailResource object from user

            MailResource mailResource = new MailResource();
            mailResource.setMessage("message");
            if(userResource != null && userResource.getEmail()!=null)
                mailResource.setRecipient(userResource.getEmail());
            else
                mailResource.setRecipient("default@mail.de");
            mailResource.setSubject("subject");

            //call fortune-cookie-mailing-service

            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(mailResource),headers);

            ResponseEntity<String> response = resilientRestTemplateCall(() -> restTemplate.postForEntity("http://localhost:8082/send", entity, String.class));

            if(response.getStatusCode().equals(HttpStatus.CREATED))
                logger.info("Mail sent successfully!");


        } catch (JsonProcessingException e) {
            logger.error("jsonPrecessingException", e);
        }
    }

    private ResponseEntity<String> resilientRestTemplateCall(Supplier<ResponseEntity<String>> function) {
        // Configure Retry strategy
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(5)
                .waitDuration(Duration.ofMillis(20))
                .retryOnResult(response -> !((ResponseEntity<String>) response).getStatusCode().equals(HttpStatus.CREATED))
                .build();
        Retry retry = Retry.of("mailer", retryConfig);

        // Decorate function with Retry
        Supplier<ResponseEntity<String>> decoratedFunction = Retry.decorateSupplier(retry, function);

        // Call decorated function
        return decoratedFunction.get();

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
