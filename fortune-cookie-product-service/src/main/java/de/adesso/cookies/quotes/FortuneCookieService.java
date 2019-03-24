package de.adesso.cookies.quotes;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class FortuneCookieService {

    private final Random random = new Random();
    private CookiesDB cookiesDB = new CookiesDB();

    private Logger logger = LoggerFactory.getLogger(FortuneCookieService.class);

    @HystrixCommand(fallbackMethod = "getCookiesFallback", groupKey = "ProductServiceGroup")
    @Cacheable(value = ProductServiceApplication.COOKIES, unless = "#result != null and #result.size() == 0")
    public ArrayList<FortuneCookieResource> getCookies(int offset, int limit) {

        // create some "stability"
        feelingLucky();
        takeYourTime();

        return cookiesDB.getList(offset, limit);
    }

    public ArrayList<FortuneCookieResource> getCookiesFallback(int offset, int limit) {
        logger.warn("Service request failed. Sending default.");

        // Fail silent
        return new ArrayList<>();
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
