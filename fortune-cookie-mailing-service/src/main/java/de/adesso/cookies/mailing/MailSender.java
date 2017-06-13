package de.adesso.cookies.mailing;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Random;

public class MailSender extends HystrixCommand {

    private Random random = new Random();

    protected MailSender() {
        super(HystrixCommandGroupKey.Factory.asKey("MailServiceGroup"));
    }

    @Override
    protected String run() throws Exception {

        feelingLucky();
        takeYourTime();

        return "Mail send successfully!";
    }

    @Override
    protected String getFallback() {
        return "Cannot send mail due to error! (Fallback)";
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
