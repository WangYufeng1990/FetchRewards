package com.fetchreward.receiptprocessor.service;

import com.fetchreward.receiptprocessor.dto.Receipt;
import com.fetchreward.receiptprocessor.dto.response.ReceiptResponse;
import com.fetchreward.receiptprocessor.exception.ReceiptNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReceiptService {

    private final Map<String, Integer> receiptStorage = new HashMap<>();
    private final ReceiptPointsCalculator receiptPointsCalculator;

    @Autowired
    public ReceiptService(ReceiptPointsCalculator receiptPointsCalculator) {
        this.receiptPointsCalculator = receiptPointsCalculator;
    }
    public ReceiptResponse processReceipt(Receipt receipt) {
        String receiptId = UUID.randomUUID().toString();
        int points = receiptPointsCalculator.calculatePoints(receipt);
        receiptStorage.put(receiptId, points);
        return new ReceiptResponse(receiptId);
    }

    public int getPoints(String receiptId) {
        Integer points = receiptStorage.get(receiptId);
        if (points == null) {
            throw new ReceiptNotFoundException("No receipt found for that ID: " + receiptId);
        }
        return points;
    }
}
