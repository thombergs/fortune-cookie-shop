package de.adesso.cookies.quotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FortuneCookieListGenerator {

    private Logger logger = LoggerFactory.getLogger(FortuneCookieListGenerator.class);

    private final Random random = new Random();

    private QuotesDB quotesDB = new QuotesDB();

    ArrayList<FortuneCookieResource> list;

    int min = 0;
    int max = 1000;

    public FortuneCookieListGenerator() {

        list = new ArrayList<>();
    }

    public ArrayList<FortuneCookieResource> generateList(int size) {

        feelingLucky();
        takeYourTime();

        ArrayList<String> quotes = quotesDB.getList(size);

        for (String quote :
                quotes) {
            list.add(new FortuneCookieResource(quote, ThreadLocalRandom.current().nextInt(min, max)));
        }

        return list;
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
