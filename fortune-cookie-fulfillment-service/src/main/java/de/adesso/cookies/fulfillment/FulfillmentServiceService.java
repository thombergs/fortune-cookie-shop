package de.adesso.cookies.fulfillment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class FulfillmentServiceService {

    private Logger logger = LoggerFactory.getLogger(FulfillmentServiceController.class);

    private Random random = new Random();

    @HystrixCommand(fallbackMethod = "mailFallback",groupKey = "FulfillmentServiceGroup")
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

            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/send",entity,String.class);

            if(response.getStatusCode().equals(HttpStatus.CREATED))
                logger.info("Mail sent successfully!");


        } catch (JsonProcessingException e) {
            logger.error("jsonPrecessingException", e);
        }
    }

    public void mailFallback(UserResource userResource) {
        logger.info("Mail sent fallback-Method completed!");
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
