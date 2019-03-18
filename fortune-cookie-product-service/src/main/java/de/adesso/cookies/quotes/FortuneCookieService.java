package de.adesso.cookies.quotes;

import io.prometheus.client.Gauge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class FortuneCookieService {

  private final Random random = new Random();
  private CookiesDB cookiesDB = new CookiesDB();
  private Gauge gauge;

  public FortuneCookieService() {
    this.gauge = Gauge.build()
            .name("number_of_products")
            .help("the number of products currently in the catalog")
            .register();
  }

  public ArrayList<FortuneCookieResource> getCookies() {

    // create some "stability"
    feelingLucky();
    takeYourTime();

    this.gauge.set(cookiesDB.getList().size());
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
