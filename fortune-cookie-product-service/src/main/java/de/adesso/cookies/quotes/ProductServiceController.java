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

    private FortuneCookieListGenerator cookieListGenerator = new FortuneCookieListGenerator();

    private Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @RequestMapping(method = RequestMethod.GET,path = "/fortuneCookieList", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ArrayList<FortuneCookieResource> fortuneCookieList() {

        ArrayList<FortuneCookieResource> cookieList = cookieListGenerator.generateList(20);

        logger.info("FortuneCookieListGenerator sent successfully!");

        return cookieList;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(final RuntimeException e) {
        logger.error("Cannot sent FortuneCookieListGenerator due to error!", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

