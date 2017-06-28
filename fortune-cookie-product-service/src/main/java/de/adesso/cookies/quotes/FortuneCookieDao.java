package de.adesso.cookies.quotes;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FortuneCookieDao {

    private final Random random = new Random();
    private CookiesDB cookiesDB = new CookiesDB();

    public ArrayList<FortuneCookieResource> getCookies() {

        // create some "stability"
        feelingLucky();
        takeYourTime();

        return cookiesDB.getList();

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
