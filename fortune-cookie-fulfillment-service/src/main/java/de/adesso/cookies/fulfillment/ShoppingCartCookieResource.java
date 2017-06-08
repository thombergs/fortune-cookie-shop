package de.adesso.cookies.fulfillment;

import javax.validation.constraints.NotNull;

public class ShoppingCartCookieResource {

    @NotNull
    private FortuneCookieResource cookie;

    @NotNull
    private int amount;

    public ShoppingCartCookieResource() {
    }

    public ShoppingCartCookieResource(FortuneCookieResource cookie, int amount) {
        this.cookie = cookie;
        this.amount = amount;
    }

    public FortuneCookieResource getCookie() {
        return cookie;
    }

    public void setCookie(FortuneCookieResource cookie) {
        this.cookie = cookie;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
