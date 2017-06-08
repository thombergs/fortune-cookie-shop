package de.adesso.cookies.fulfillment;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class ShoppingCartResource {

    @NotNull
    private ArrayList<ShoppingCartCookieResource> shoppingCartCookieResourceArrayList;

    @NotNull
    private UserResource user;

    public ShoppingCartResource() {
    }

    public ShoppingCartResource(ArrayList<ShoppingCartCookieResource> shoppingCartCookieResourceArrayList, UserResource user) {
        this.shoppingCartCookieResourceArrayList = shoppingCartCookieResourceArrayList;
        this.user = user;
    }

    public ArrayList<ShoppingCartCookieResource> getShoppingCartCookieResourceArrayList() {
        return shoppingCartCookieResourceArrayList;
    }

    public void setShoppingCartCookieResourceArrayList(ArrayList<ShoppingCartCookieResource> shoppingCartCookieResourceArrayList) {
        this.shoppingCartCookieResourceArrayList = shoppingCartCookieResourceArrayList;
    }

    public UserResource getUser() {
        return user;
    }

    public void setUser(UserResource user) {
        this.user = user;
    }
}
