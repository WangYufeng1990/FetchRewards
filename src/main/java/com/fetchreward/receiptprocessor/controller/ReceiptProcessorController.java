package com.fetchreward.receiptprocessor.controller;

import com.fetchreward.receiptprocessor.dto.Receipt;
import com.fetchreward.receiptprocessor.dto.response.PointsResponse;
import com.fetchreward.receiptprocessor.dto.response.ReceiptResponse;
import com.fetchreward.receiptprocessor.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/receipts")
public class ReceiptProcessorController {

    private final ReceiptService receiptService;

    @Autowired
    public ReceiptProcessorController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<ReceiptResponse> submitReceipt(@Valid @RequestBody Receipt request) {
        ReceiptResponse receiptResponse = receiptService.processReceipt(request);
        return ResponseEntity.ok(receiptResponse);
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<PointsResponse> getPoints(@PathVariable String id) {
        int points = receiptService.getPoints(id);
        return ResponseEntity.ok(new PointsResponse(points));
    }

}
