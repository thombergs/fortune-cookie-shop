package de.adesso.cookies.quotes;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FortuneCookieListGenerator extends HystrixCommand {

    private Logger logger = LoggerFactory.getLogger(FortuneCookieListGenerator.class);

    private final Random random = new Random();

    private QuotesDB quotesDB = new QuotesDB();

    private ArrayList<FortuneCookieResource> list;

    private int min = 0;
    private int max = 1000;
    private int size = 0;

    public FortuneCookieListGenerator(int size) {
        super(HystrixCommandGroupKey.Factory.asKey("ProductServiceGroup"),4500);

        list = new ArrayList<>();
        this.size = size;
    }

    @Override
    protected ArrayList<FortuneCookieResource> run() throws Exception {

        feelingLucky();
        takeYourTime();

        ArrayList<String> quotes = quotesDB.getList(size);

        for (String quote :
                quotes) {
            list.add(new FortuneCookieResource(quote, ThreadLocalRandom.current().nextInt(min, max)));
        }

        return list;
    }

    @Override
    protected ArrayList<FortuneCookieResource> getFallback() {
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
