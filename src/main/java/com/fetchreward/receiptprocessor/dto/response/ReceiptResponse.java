package com.fetchreward.receiptprocessor.dto.response;

public class ReceiptResponse {
    private String id;

    public ReceiptResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
