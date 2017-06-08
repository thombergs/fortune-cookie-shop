package de.adesso.cookies.fulfillment;

import javax.validation.constraints.NotNull;

public class UserResource {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String address;

    @NotNull
    private String zipCode;

    @NotNull
    private String city;

    @NotNull
    private String email;

    public UserResource() {
    }

    public UserResource(String name, String surname, String address, String zipCode, String city) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
