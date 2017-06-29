package de.adesso.cookies.quotes;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class FortuneCookieDao {

    private Logger logger = LoggerFactory.getLogger(FortuneCookieDao.class);

    private final Random random = new Random();
    private CookiesDB cookiesDB = new CookiesDB();

    @HystrixCommand(fallbackMethod = "getCookiesFallback", groupKey = "ProductServiceGroup")
    @Cacheable("cookies")
    public ArrayList<FortuneCookieResource> getCookies(int offset, int limit) {

        feelingLucky();
        takeYourTime();

        return cookiesDB.getList(offset, limit);
    }

    public ArrayList<FortuneCookieResource> getCookiesFallback(int offset, int limit) {
        logger.warn("Failed...");

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
