package de.adesso.cookies.quotes;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/product")
public class ProductServiceController {

    private Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @Autowired
    private FortuneCookieService fortuneCookieService;

    private Timer timer;

    @Autowired
    public ProductServiceController(MetricRegistry metricRegistry) {
        this.timer = metricRegistry.timer(ProductServiceController.class.getSimpleName());
    }

    @RequestMapping(method = RequestMethod.GET,path = "/fortuneCookieList", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ArrayList<FortuneCookieResource> getFortuneCookieList() {
        Timer.Context context = timer.time();
        try {
            ArrayList<FortuneCookieResource> cookieList = fortuneCookieService.getCookies();

            logger.info("FortuneCookies sent successfully!");
            return cookieList;
        }finally {
            context.stop();
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(final RuntimeException e) {
        logger.error("Cannot sent FortuneCookieDao due to error!", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

