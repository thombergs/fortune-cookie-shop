package de.adesso.cookies.mailing;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class MailService extends HystrixCommand<Void> {

    private Random random = new Random();
    private MailResource mailResource;
    private Logger logger = LoggerFactory.getLogger(MailService.class);

    protected MailService(MailResource mailResource) {
        super(HystrixCommandGroupKey.Factory.asKey("MailServiceGroup"), 4500);
        this.mailResource = mailResource;
    }

    @Override
    protected Void run() throws RuntimeException {
        feelingLucky();
        takeYourTime();

        // SENDING MAIL... :)

        logger.info("Mail send successfully!");
        return null;
    }

    @Override
    protected Void getFallback() {
        logger.info("Cannot send mail due to error! (Fallback)");
        return null;
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
        } catch (InterruptedException e) {
            throw new RuntimeException("BWAAAAAH!", e);
        }

        if (milliseconds > 4500) {
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                throw new RuntimeException("BOOOOH!", e);
            }
        }
    }
}
