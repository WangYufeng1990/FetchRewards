package com.fetchreward.receiptprocessor.controller;
import com.fetchreward.receiptprocessor.dto.Item;
import com.fetchreward.receiptprocessor.dto.Receipt;
import com.fetchreward.receiptprocessor.dto.response.ReceiptResponse;
import com.fetchreward.receiptprocessor.service.ReceiptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@ExtendWith(MockitoExtension.class)
public class ReceiptProcessorControllerTest {

    @Mock
    private ReceiptService receiptService;

    @InjectMocks
    private ReceiptProcessorController receiptProcessorController;

    private MockMvc mockMvc; // MockMvc to simulate HTTP requests
    private ObjectMapper objectMapper; // ObjectMapper to convert objects to JSON

    @BeforeEach
    void setUp() {
        // initialization
        mockMvc = MockMvcBuilders.standaloneSetup(receiptProcessorController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSubmitReceiptValidData() throws Exception {
        // Create a valid Receipt object with mock data
        Receipt validReceipt = new Receipt();
        validReceipt.setRetailer("Valid Retailer");
        validReceipt.setPurchaseDate("2025-01-01");
        validReceipt.setPurchaseTime("12:00");
        validReceipt.setTotal("10.00");
        validReceipt.setItems(Arrays.asList(new Item("item1", "5.00")));

        ReceiptResponse receiptResponse = new ReceiptResponse("Processed successfully");
        when(receiptService.processReceipt(any(Receipt.class))).thenReturn(receiptResponse);

        mockMvc.perform(post("/receipts/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validReceipt)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testSubmitReceiptInvalidData() throws Exception {
        // Create an invalid Receipt object with invalid data
        Receipt invalidReceipt = new Receipt();
        invalidReceipt.setRetailer("");
        invalidReceipt.setPurchaseDate("invalid-date");
        invalidReceipt.setPurchaseTime("invalid-time");
        invalidReceipt.setTotal("10.00");
        invalidReceipt.setItems(Arrays.asList(new Item("item1", "5.00")));

        mockMvc.perform(post("/receipts/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidReceipt)))
                .andExpect(status().isBadRequest()); // Expect HTTP 400 Bad Request status
    }

    @Test
    void testGetPoints() throws Exception {
        String id = "12345";
        int points = 100;

        when(receiptService.getPoints(id)).thenReturn(points);


        mockMvc.perform(get("/receipts/{id}/points", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points").value(points));
    }
}
