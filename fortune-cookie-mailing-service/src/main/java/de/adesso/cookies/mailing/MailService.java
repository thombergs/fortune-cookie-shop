package de.adesso.cookies.mailing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class MailService {

    private Random random = new Random();
    private Logger logger = LoggerFactory.getLogger(MailService.class);


    public void sendMail(MailResource mailResource) {
        feelingLucky();
        takeYourTime();

        // SENDING MAIL... :)

        logger.info("Mail send successfully!");
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
