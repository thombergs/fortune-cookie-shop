package de.adesso.cookies.quotes;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import java.util.ArrayList;
import java.util.Random;

public class FortuneCookieDao extends HystrixCommand<ArrayList<FortuneCookieResource>> {

    private final Random random = new Random();
    private CookiesDB cookiesDB = new CookiesDB();

    private int offset = 0;
    private int limit = 0;

    public FortuneCookieDao(int offset, int limit) {
        super(HystrixCommandGroupKey.Factory.asKey("ProductServiceGroup"),4500);

        this.offset = offset;
        this.limit = limit;
    }

    @Override
    protected ArrayList<FortuneCookieResource> run() throws Exception {

        feelingLucky();
        takeYourTime();

        return cookiesDB.getList(offset, limit);
    }

    @Override
    protected ArrayList<FortuneCookieResource> getFallback() {
        // Fail silent
        return new ArrayList<>();
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(offset) + "-" + String.valueOf(limit);
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
