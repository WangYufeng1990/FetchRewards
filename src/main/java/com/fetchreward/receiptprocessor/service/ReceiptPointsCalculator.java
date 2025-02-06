package com.fetchreward.receiptprocessor.service;

import com.fetchreward.receiptprocessor.dto.Item;
import com.fetchreward.receiptprocessor.dto.Receipt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReceiptPointsCalculator {

    public int calculatePoints(Receipt receipt) {
        int points = 0;
        points += getRetailerNamePoints(receipt.getRetailer());
        points += getTotalAmountPoints(receipt.getTotal());
        points += getItemCountPoints(receipt.getItems());
        points += getItemDescriptionPoints(receipt.getItems());
        points += getPurchaseDatePoints(receipt.getPurchaseDate());
        points += getPurchaseTimePoints(receipt.getPurchaseTime());
        return points;
    }

    private int getRetailerNamePoints(String retailer) {
        return (int) retailer.chars().filter(Character::isLetterOrDigit).count();
    }

    private int getTotalAmountPoints(String total) {
        double amount = Double.parseDouble(total);
        int points = 0;
        if (amount % 1 == 0) points += 50;
        if (amount % 0.25 == 0) points += 25;
        return points;
    }

    private int getItemCountPoints(List<Item> items) {
        return (items.size() / 2) * 5;
    }

    private int getItemDescriptionPoints(List<Item> items) {
        int points = 0;
        for (Item item : items) {
            String desc = item.getShortDescription().trim();
            if (desc.length() % 3 == 0) {
                points += Math.ceil(Double.parseDouble(item.getPrice()) * 0.2);
            }
        }
        return points;
    }

    private int getPurchaseDatePoints(String purchaseDate) {
        LocalDate date = LocalDate.parse(purchaseDate);
        return date.getDayOfMonth() % 2 != 0 ? 6 : 0;
    }

    private int getPurchaseTimePoints(String purchaseTime) {
        LocalTime time = LocalTime.parse(purchaseTime);
        return (time.isAfter(LocalTime.of(14, 0)) && time.isBefore(LocalTime.of(16, 0))) ? 10 : 0;
    }
}