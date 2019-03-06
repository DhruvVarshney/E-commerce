package com.sample.request;

import java.util.HashMap;
import java.util.Map;

public class PlaceOrderRequest {

    private String emailId;
    private Map<Integer,Integer> itemIdAndQuantity = new HashMap<>();
    private Double amount;
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Map<Integer, Integer> getItemIdAndQuantity() {
        return itemIdAndQuantity;
    }

    public void setItemIdAndQuantity(Map<Integer, Integer> itemIdAndQuantity) {
        this.itemIdAndQuantity = itemIdAndQuantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
