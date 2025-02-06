package com.fetchreward.receiptprocessor.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class Receipt {

    @NotBlank(message = "Retailer name is required.")
    @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Retailer name is invalid.")
    private String retailer;

    @NotBlank(message = "Purchase date is required.")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Purchase date must be in format YYYY-MM-DD.")
    private String purchaseDate;

    @NotBlank(message = "Purchase time is required.")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Purchase time must be in format HH:mm.")
    private String purchaseTime;

    @NotEmpty(message = "At least one item is required.")
    @Size(min = 1, message = "Receipt must have at least one item.")
    private List<Item> items;

    @NotBlank(message = "Total amount is required.")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Total amount is invalid.")
    private String total;

    public Receipt(String retailer, String purchaseDate, String purchaseTime, List<Item> items, String total) {
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.items = items;
        this.total = total;
    }

    public Receipt() {

    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}