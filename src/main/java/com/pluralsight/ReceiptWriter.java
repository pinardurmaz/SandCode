package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {

    private static final String RECEIPTS_FOLDER_NAME = "receipts";

    // Creates the 'receipts' folder if it doesn't already exist.
    public static void createReceiptsFolder() {
        File receiptsFolder = new File(RECEIPTS_FOLDER_NAME);
        System.out.println("Attempting to create/verify folder: " + receiptsFolder.getAbsolutePath());
        if (!receiptsFolder.exists()) {
            boolean created = receiptsFolder.mkdir();
            if (created) {
                System.out.println("'" + RECEIPTS_FOLDER_NAME + "' folder created successfully at: " + receiptsFolder.getAbsolutePath());
            } else {
                System.err.println("ERROR: Could not create '" + RECEIPTS_FOLDER_NAME + "' folder at: " + receiptsFolder.getAbsolutePath() + ". Please check directory permissions.");
            }
        } else {
            System.out.println("'" + RECEIPTS_FOLDER_NAME + "' folder already exists at: " + receiptsFolder.getAbsolutePath());
        }
    }

    // Saves the order details to a receipt file.
    public static void saveReceipt(Order order) {
        System.out.println("Attempting to save receipt...");
        createReceiptsFolder(); // Ensure the folder exists

        // Generate file name based on current date and time (yyyyMMdd-hhmmss.txt)
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        String fileName = now.format(formatter) + ".txt";

        // Create the full file path
        String fullFilePath = RECEIPTS_FOLDER_NAME + File.separator + fileName;
        System.out.println("Receipt file path: " + new File(fullFilePath).getAbsolutePath());

        // Get the receipt content from the Order object
        String receiptContent = order.generateReceiptContent();
        System.out.println("Receipt content generated. Length: " + receiptContent.length() + " characters.");

        // Write the receipt content to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullFilePath))) {
            writer.write(receiptContent);
            System.out.println("Receipt successfully saved to: " + fullFilePath);
        } catch (IOException e) {
            System.err.println("An error occurred while writing the receipt file: " + e.getMessage());
            System.err.println("Possible causes: permissions, invalid path, disk full.");
            e.printStackTrace();
        }
    }
}
