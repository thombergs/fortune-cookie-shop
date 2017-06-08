package de.adesso.cookies.fulfillment;

import javax.validation.constraints.NotNull;

public class FortuneCookieResource {

    public FortuneCookieResource() {
    }

    public FortuneCookieResource(String quote, int price) {
        this.quote = quote;
        this.price = price;
    }

    @NotNull
    String quote;

    @NotNull
    int price;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
