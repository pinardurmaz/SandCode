package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 6. ReceiptWriter.java - Writes order to a timestamped .txt file
public class ReceiptWriter {
    public static void write(Order order) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String fileName = "receipts/" + timestamp + ".txt";

            FileWriter writer = new FileWriter(fileName);
            writer.write("=== SANDCODE RECEIPT ===\n");
            writer.write(order.getReceiptText());
            writer.close();

            System.out.println("Receipt saved as: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}