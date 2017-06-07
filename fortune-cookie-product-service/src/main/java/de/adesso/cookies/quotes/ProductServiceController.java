package de.adesso.cookies.quotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

@RestController
@RequestMapping("/product")
public class ProductServiceController{

    private final Random random = new Random();

    private Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @RequestMapping(method = RequestMethod.GET,path = "/fortuneCookieList", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ArrayList<FortuneCookieResource> fortuneCookieList() {

        feelingLucky();
        takeYourTime();

        FortuneCookieList cookieListGenerator = new FortuneCookieList();
        ArrayList<FortuneCookieResource> cookieList = cookieListGenerator.generateList(20);

        logger.info("FortuneCookieList sent successfully!");

        return cookieList;

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

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(final RuntimeException e) {
        logger.error("Cannot sent FortuneCookieList due to error!", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

