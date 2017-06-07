package de.adesso.cookies.quotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class FortuneCookieList {

    private Logger logger = LoggerFactory.getLogger(FortuneCookieList.class);

    private QuotesDB quotesDB = new QuotesDB();

    ArrayList<FortuneCookieResource> list;

    int min = 0;
    int max = 1000;

    public FortuneCookieList() {

        list = new ArrayList<>();
    }

    public ArrayList<FortuneCookieResource> generateList(int size) {

        ArrayList<String> quotes = quotesDB.getList(size);

        for (String quote :
                quotes) {
            list.add(new FortuneCookieResource(quote, ThreadLocalRandom.current().nextInt(min, max)));
        }

        return list;
    }

}
