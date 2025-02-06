package com.fetchreward.receiptprocessor.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Item {

    @NotBlank(message = "Item description is required.")
    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "shortDescription is invalid.")
    private String shortDescription;

    @NotBlank(message = "Item price is required.")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "price is invalid.")
    private String price;

    public Item() {
    }

    public Item(String shortDescription, String price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
