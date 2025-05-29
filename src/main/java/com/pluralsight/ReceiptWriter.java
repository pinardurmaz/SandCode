package com.pluralsight; // Paket bildirimi

import java.io.BufferedWriter; // BufferedWriter sınıfını kullanmak için
import java.io.File; // File sınıfını kullanmak için
import java.io.FileWriter; // FileWriter sınıfını kullanmak için
import java.io.IOException; // IOException sınıfını kullanmak için
import java.time.LocalDateTime; // LocalDateTime sınıfını kullanmak için
import java.time.format.DateTimeFormatter; // DateTimeFormatter sınıfını kullanmak için

public class ReceiptWriter {

    // Fişlerin kaydedileceği klasör adı, proje gereksinimlerine göre "receipts" olarak ayarlandı.
    private static final String RECEIPTS_FOLDER_NAME = "receipts";

    // Creates the 'receipts' folder if it doesn't already exist.
    // 'receipts' klasörünü yoksa oluşturur.
    public static void createReceiptsFolder() {
        File receiptsFolder = new File(RECEIPTS_FOLDER_NAME); // Klasör için File nesnesi oluştur
        System.out.println("Attempting to create/verify folder: " + receiptsFolder.getAbsolutePath()); // Klasör oluşturma/doğrulama girişimini logla
        if (!receiptsFolder.exists()) { // Klasör yoksa
            boolean created = receiptsFolder.mkdir(); // Klasörü oluştur
            if (created) {
                System.out.println("'" + RECEIPTS_FOLDER_NAME + "' folder created successfully at: " + receiptsFolder.getAbsolutePath()); // Başarıyla oluşturuldu mesajı
            } else {
                System.err.println("ERROR: Could not create '" + RECEIPTS_FOLDER_NAME + "' folder at: " + receiptsFolder.getAbsolutePath() + ". Please check directory permissions."); // Hata mesajı
            }
        } else {
            System.out.println("'" + RECEIPTS_FOLDER_NAME + "' folder already exists at: " + receiptsFolder.getAbsolutePath()); // Klasör zaten var mesajı
        }
    }

    // Saves the order details to a receipt file.
    // Sipariş detaylarını bir fiş dosyasına kaydeder.
    public static void saveReceipt(Order order) {
        System.out.println("Attempting to save receipt..."); // Fiş kaydetme girişimini logla
        createReceiptsFolder(); // Ensure the folder exists - Klasörün var olduğundan emin ol

        // Generate file name based on current date and time (yyyyMMdd-hhmmss.txt)
        // Mevcut tarih ve saate göre dosya adını oluştur (yyyyMMdd-hhmmss.txt)
        LocalDateTime now = LocalDateTime.now(); // Şimdiki tarih ve saati al
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss"); // Formatlayıcı oluştur
        String fileName = now.format(formatter) + ".txt"; // Dosya adını oluştur

        // Create the full file path - Tam dosya yolunu oluştur
        String fullFilePath = RECEIPTS_FOLDER_NAME + File.separator + fileName; // Klasör adı ve dosya adını birleştir
        System.out.println("Receipt file path: " + new File(fullFilePath).getAbsolutePath()); // Fiş dosya yolunu logla

        // Get the receipt content from the Order object - Fiş içeriğini Order nesnesinden al
        String receiptContent = order.generateReceiptContent();
        System.out.println("Receipt content generated. Length: " + receiptContent.length() + " characters."); // Fiş içeriği uzunluğunu logla

        // Write the receipt content to the file - Fiş içeriğini dosyaya yaz
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullFilePath))) { // BufferedWriter kullanarak dosyayı yaz
            writer.write(receiptContent); // İçeriği yaz
            System.out.println("Receipt successfully saved to: " + fullFilePath); // Başarıyla kaydedildi mesajı
        } catch (IOException e) { // IOException yakala
            System.err.println("An error occurred while writing the receipt file: " + e.getMessage()); // Hata mesajı
            System.err.println("Possible causes: permissions, invalid path, disk full."); // Olası nedenleri belirt
            e.printStackTrace(); // Hata izini yazdır
        }
    }
}
