package com.pluralsight; // Paket bildirimi

import java.util.ArrayList; // ArrayList sınıfını kullanmak için
import java.util.List; // List arayüzünü kullanmak için
import java.util.Scanner; // Scanner sınıfını kullanmak için

public class Order {
    private List<Sandwich> sandwiches; // Siparişteki sandviçlerin listesi
    private List<Drink> drinks; // Siparişteki içeceklerin listesi
    private List<Chip> chips; // Siparişteki cipslerin listesi
    private Scanner scanner; // Kullanıcıdan girdi almak için scanner

    public Order(Scanner scanner) { // Constructor, scanner'ı parametre olarak alır
        this.sandwiches = new ArrayList<>(); // Sandviç listesini başlat
        this.drinks = new ArrayList<>(); // İçecek listesini başlat
        this.chips = new ArrayList<>(); // Cips listesini başlat
        this.scanner = scanner; // Scanner'ı ata
    }

    // Adds a sandwich to the order.
    public void addSandwich(Sandwich sandwich) {
        this.sandwiches.add(sandwich); // Sandviçi listeye ekle
    }

    // Adds a drink to the order.
    public void addDrink(Drink drink) {
        this.drinks.add(drink); // İçeceği listeye ekle
    }

    // Adds chips to the order.
    public void addChip(Chip chip) {
        this.chips.add(chip); // Cipsi listeye ekle
    }

    // Calculates the total cost of the order.
    public double calculateTotalCost() {
        double totalCost = 0.0; // Toplam maliyeti sıfırla
        for (Sandwich s : sandwiches) {
            totalCost += s.calculateCost(); // Her sandviçin maliyetini ekle
        }
        for (Drink d : drinks) {
            totalCost += d.getCost(); // Her içeceğin maliyetini ekle
        }
        for (Chip c : chips) {
            totalCost += c.getCost(); // Her cipsin maliyetini ekle
        }
        return totalCost; // Toplam maliyeti döndür
    }

    // Displays the detailed order summary to the console in a receipt-like format.
    // Detaylı sipariş özetini konsola fiş benzeri bir formatta gösterir.
    public void displayOrderDetails() {
        System.out.println("\n===================================="); // Fiş başlangıç çizgisi
        System.out.println("          SandCode Order             "); // Fiş başlığı
        System.out.println("===================================="); // Fiş başlık alt çizgisi

        if (sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty()) {
            System.out.println("Your order is currently empty."); // Siparişin boş olduğunu belirt
            System.out.println("===================================="); // Fiş bitiş çizgisi
            return;
        }

        System.out.println("\n--- SANDWICHES ---"); // Sandviçler bölüm başlığı
        if (sandwiches.isEmpty()) {
            System.out.println("  No sandwiches added."); // Sandviç yoksa
        } else {
            for (int i = 0; i < sandwiches.size(); i++) {
                System.out.println("  #" + (i + 1) + " Sandwich Details:"); // Sandviç numarası
                // Sandwich.getDetails() metodu zaten detayları formatlıyor
                // Burada her satırın başında boşluk bırakarak daha iyi hizalama sağlayabiliriz
                String[] sandwichDetails = sandwiches.get(i).getDetails().split("\n");
                for (String detailLine : sandwichDetails) {
                    System.out.println("  " + detailLine); // Her detay satırını girintili yazdır
                }
                System.out.println("  --------------------"); // Sandviç ayırıcı
            }
        }

        System.out.println("\n--- DRINKS ---"); // İçecekler bölüm başlığı
        if (drinks.isEmpty()) {
            System.out.println("  No drinks added."); // İçecek yoksa
        } else {
            for (Drink d : drinks) {
                System.out.printf("  - %-15s %-10s $%.2f\n", d.getSize(), d.getFlavor(), d.getCost()); // İçecek detaylarını formatlı yazdır
            }
        }

        System.out.println("\n--- CHIPS ---"); // Cipsler bölüm başlığı
        if (chips.isEmpty()) {
            System.out.println("  No chips added."); // Cips yoksa
        } else {
            for (Chip c : chips) {
                System.out.printf("  - %-25s $%.2f\n", c.getType(), c.getCost()); // Cips detaylarını formatlı yazdır
            }
        }

        System.out.println("\n===================================="); // Toplam öncesi ayırıcı
        System.out.printf("TOTAL AMOUNT:             $%.2f\n", calculateTotalCost()); // Toplam tutarı formatlı yazdır
        System.out.println("===================================="); // Fiş bitiş çizgisi
        System.out.println("  Thank you for your order!         "); // Teşekkür mesajı
        System.out.println("===================================="); // Fiş bitiş çizgisi
    }

    // Generates the receipt content as a String.
    public String generateReceiptContent() {
        StringBuilder receipt = new StringBuilder(); // Fiş içeriği için StringBuilder
        receipt.append("====================================\n"); // Fiş başlangıç çizgisi
        receipt.append("          DELI-cious Receipt        \n"); // Fiş başlığı
        receipt.append("====================================\n"); // Fiş başlık alt çizgisi
        receipt.append("Date: ").append(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n"); // Tarih ve saat
        receipt.append("------------------------------------\n"); // Ayırıcı
        receipt.append("Order Details:\n"); // Sipariş detayları başlığı

        if (!sandwiches.isEmpty()) {
            receipt.append("\n--- SANDWICHES ---\n"); // Sandviçler başlığı
            for (Sandwich s : sandwiches) {
                String[] sandwichDetails = s.getDetails().split("\n");
                for (String detailLine : sandwichDetails) {
                    receipt.append("  ").append(detailLine).append("\n"); // Her detay satırını girintili ekle
                }
                receipt.append("  --------------------\n"); // Sandviç ayırıcı
            }
        }
        if (!drinks.isEmpty()) {
            receipt.append("\n--- DRINKS ---\n"); // İçecekler başlığı
            for (Drink d : drinks) {
                receipt.append(String.format("  - %-15s %-10s $%.2f\n", d.getSize(), d.getFlavor(), d.getCost())); // İçecek detaylarını formatlı ekle
            }
        }
        if (!chips.isEmpty()) {
            receipt.append("\n--- CHIPS ---\n"); // Cipsler başlığı
            for (Chip c : chips) {
                receipt.append(String.format("  - %-25s $%.2f\n", c.getType(), c.getCost())); // Cips detaylarını formatlı ekle
            }
        }

        receipt.append("\n====================================\n"); // Toplam öncesi ayırıcı
        receipt.append(String.format("TOTAL AMOUNT:             $%.2f\n", calculateTotalCost())); // Toplam tutarı formatlı ekle
        receipt.append("====================================\n"); // Fiş bitiş çizgisi
        receipt.append("  Thank you for your order!         \n"); // Teşekkür mesajı
        receipt.append("====================================\n"); // Fiş bitiş çizgisi
        return receipt.toString(); // Fiş içeriğini döndür
    }

    // Handles the flow of adding items to the order.
    public void handleOrderFlow() {
        int orderChoice; // Sipariş menüsü seçimi
        do {
            displayOrderMenu(); // Sipariş menüsünü göster
            orderChoice = getUserChoice(); // Kullanıcının seçimini al

            switch (orderChoice) {
                case 1:
                    addSandwichToOrder(); // Sandviç ekle
                    break;
                case 2:
                    addDrinkToOrder(); // İçecek ekle
                    break;
                case 3:
                    addChipsToOrder(); // Cips ekle
                    break;
                case 4:
                    checkoutOrder(); // Ödeme yap
                    orderChoice = 0; // Checkout sonrası ana menüye dönmek için döngüden çık
                    break;
                case 0:
                    cancelOrder(); // Siparişi iptal et
                    orderChoice = 0; // Siparişi iptal ettikten sonra ana menüye dönmek için döngüden çık
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); // Geçersiz seçenek mesajı
            }
        } while (orderChoice != 0); // Kullanıcı 0'ı seçene kadar döngü devam et
    }

    // Displays the order menu options.
    private void displayOrderMenu() {
        System.out.println("\n--- Order Menu ---"); // Sipariş menüsü başlığı
        System.out.println("1) Add Sandwich"); // Sandviç ekle seçeneği
        System.out.println("2) Add Drink"); // İçecek ekle seçeneği
        System.out.println("3) Add Chips"); // Cips ekle seçeneği
        System.out.println("4) Checkout"); // Ödeme yap seçeneği
        System.out.println("0) Cancel Order"); // Siparişi iptal et seçeneği
        System.out.print("Enter your choice: "); // Kullanıcıdan seçim iste
    }

    // Gets integer input from the user, handling invalid inputs.
    private int getUserChoice() {
        while (!scanner.hasNextInt()) { // Kullanıcı tam sayı girmeyene kadar
            System.out.println("Invalid input. Please enter a number."); // Geçersiz giriş mesajı
            scanner.next(); // Yanlış girdiyi tüket
            System.out.print("Enter your choice: "); // Tekrar seçim iste
        }
        int choice = scanner.nextInt(); // Sayıyı al
        scanner.nextLine(); // Boş satırı tüket (enter tuşu için)
        return choice; // Seçimi döndür
    }

    // Guides the user through adding a sandwich to the current order.
    // Kullanıcıya mevcut siparişe sandviç ekleme sürecinde rehberlik eder.
    private void addSandwichToOrder() {
        System.out.println("\n--- Add Sandwich ---"); // Sandviç ekle başlığı
        System.out.println("1) Choose a Signature Sandwich"); // İmzalı sandviç seçeneği
        System.out.println("2) Build a Custom Sandwich"); // Özel sandviç oluştur seçeneği
        System.out.print("Enter your choice: "); // Kullanıcıdan seçim iste

        int sandwichTypeChoice = getUserChoice(); // Sandviç türü seçimini al

        if (sandwichTypeChoice == 1) {
            addSignatureSandwich(); // İmzalı sandviç ekle
        } else if (sandwichTypeChoice == 2) {
            addCustomSandwich(); // Özel sandviç ekle
        } else {
            System.out.println("Invalid choice. Returning to order menu."); // Geçersiz seçim mesajı
        }
    }

    // Guides the user through adding a signature sandwich.
    // Kullanıcıya imzalı sandviç ekleme sürecinde rehberlik eder.
    private void addSignatureSandwich() {
        System.out.println("\n--- Choose Signature Sandwich ---"); // İmzalı sandviç seç başlığı
        System.out.println("1) BLT (White Bread, Bacon, Cheddar, Lettuce, Tomato, Ranch, Toasted)"); // BLT seçeneği
        System.out.println("2) Philly Cheese Steak (White Bread, Steak, American Cheese, Peppers, Mayo, Toasted)"); // Philly Cheese Steak seçeneği
        System.out.print("Enter your choice: "); // Kullanıcıdan seçim iste

        int signatureChoice = getUserChoice(); // İmzalı sandviç seçimini al
        int size = 8; // Signature sandwiches are typically 8" - İmzalı sandviçler genellikle 8" boyutundadır

        Sandwich signatureSandwich = null; // İmzalı sandviç nesnesi
        switch (signatureChoice) {
            case 1:
                signatureSandwich = SignatureSandwich.createBLT(size); // BLT oluştur
                break;
            case 2:
                signatureSandwich = SignatureSandwich.createPhillyCheeseSteak(size); // Philly Cheese Steak oluştur
                break;
            default:
                System.out.println("Invalid signature sandwich choice."); // Geçersiz seçim mesajı
                return;
        }

        addSandwich(signatureSandwich); // İmzalı sandviçi siparişe ekle
        System.out.println(signatureSandwich.getBreadType() + " " + signatureSandwich.getSize() + "\" " + "Signature Sandwich added to order."); // Eklendi mesajı
    }

    // Guides the user through building a custom sandwich, allowing comma-separated inputs for toppings.
    // Kullanıcıya özel sandviç oluşturma sürecinde rehberlik eder, toppingler için virgülle ayrılmış girdilere izin verir.
    private void addCustomSandwich() {
        System.out.println("\n--- Build Custom Sandwich ---"); // Özel sandviç oluştur başlığı

        System.out.print("Select your bread (white, wheat, rye, wrap): "); // Ekmek türü iste
        String bread = scanner.nextLine();

        int size = 0; // Boyut değişkeni
        boolean validSize = false; // Geçerli boyut kontrolü
        while (!validSize) {
            System.out.print("Select sandwich size (4, 8, 12): "); // Boyut iste
            size = getUserChoice(); // Boyutu al
            if (size == 4 || size == 8 || size == 12) {
                validSize = true; // Geçerli boyut
            } else {
                System.out.println("Invalid size. Please choose 4, 8, or 12."); // Geçersiz boyut mesajı
            }
        }

        Sandwich newSandwich = new Sandwich(size, bread); // Yeni sandviç nesnesi oluştur

        System.out.print("Would you like the sandwich toasted (Y/N)? "); // Kızartma isteği
        String toastedInput = scanner.nextLine().trim().toUpperCase();
        newSandwich.setToasted(toastedInput.equals("Y")); // Kızartma durumunu ayarla

        // Add Meats
        System.out.println("\n--- Add Meats ---"); // Et ekleme başlığı
        System.out.println("Available: steak, ham, salami, roast beef, chicken, bacon"); // Mevcut etler
        System.out.print("Enter meat types (comma-separated, leave blank for none): "); // Et türleri iste
        String meatInputLine = scanner.nextLine().trim(); // Tüm satırı al
        if (!meatInputLine.isEmpty()) { // Boş değilse işle
            String[] meatTypes = meatInputLine.split(","); // Virgülle ayır
            for (String meatType : meatTypes) { // Her bir et türü için
                String trimmedMeatType = meatType.trim(); // Boşlukları temizle
                if (!trimmedMeatType.isEmpty()) { // Boş değilse
                    System.out.print("Add extra " + trimmedMeatType + " (Y/N)? "); // Ekstra et isteği
                    boolean isExtraMeat = scanner.nextLine().trim().toUpperCase().equals("Y");
                    newSandwich.addMeat(new Topping(trimmedMeatType, true, isExtraMeat)); // Eti sandviçe ekle
                    System.out.println(trimmedMeatType + " added."); // Eklendi mesajı
                }
            }
        }

        // Add Cheeses
        System.out.println("\n--- Add Cheeses ---"); // Peynir ekleme başlığı
        System.out.println("Available: american, provolone, cheddar, swiss"); // Mevcut peynirler
        System.out.print("Enter cheese types (comma-separated, leave blank for none): "); // Peynir türleri iste
        String cheeseInputLine = scanner.nextLine().trim(); // Tüm satırı al
        if (!cheeseInputLine.isEmpty()) { // Boş değilse işle
            String[] cheeseTypes = cheeseInputLine.split(","); // Virgülle ayır
            for (String cheeseType : cheeseTypes) { // Her bir peynir türü için
                String trimmedCheeseType = cheeseType.trim(); // Boşlukları temizle
                if (!trimmedCheeseType.isEmpty()) { // Boş değilse
                    System.out.print("Add extra " + trimmedCheeseType + " (Y/N)? "); // Ekstra peynir isteği
                    boolean isExtraCheese = scanner.nextLine().trim().toUpperCase().equals("Y");
                    newSandwich.addCheese(new Topping(trimmedCheeseType, true, isExtraCheese)); // Peyniri sandviçe ekle
                    System.out.println(trimmedCheeseType + " added."); // Eklendi mesajı
                }
            }
        }

        // Add Regular Toppings
        System.out.println("\n--- Add Regular Toppings ---"); // Normal topping ekleme başlığı
        System.out.println("Available: lettuce, peppers, onions, tomatoes, jalapeños, cucumbers, pickles, guacamole, mushrooms"); // Mevcut normal toppingler
        System.out.print("Enter toppings (comma-separated, leave blank for none): "); // Topping türleri iste
        String regularToppingInputLine = scanner.nextLine().trim(); // Tüm satırı al
        if (!regularToppingInputLine.isEmpty()) { // Boş değilse işle
            String[] regularToppingTypes = regularToppingInputLine.split(","); // Virgülle ayır
            for (String toppingType : regularToppingTypes) { // Her bir topping türü için
                String trimmedToppingType = toppingType.trim(); // Boşlukları temizle
                if (!trimmedToppingType.isEmpty()) { // Boş değilse
                    newSandwich.addRegularTopping(new Topping(trimmedToppingType, false, false)); // Toppingi sandviçe ekle
                    System.out.println(trimmedToppingType + " added."); // Eklendi mesajı
                }
            }
        }

        // Add Sauces
        System.out.println("\n--- Add Sauces ---"); // Sos ekleme başlığı
        System.out.println("Available: mayo, mustard, ketchup, ranch, thousand islands, vinaigrette, au jus"); // Mevcut soslar
        System.out.print("Enter sauces (comma-separated, leave blank for none): "); // Sos türleri iste
        String sauceInputLine = scanner.nextLine().trim(); // Tüm satırı al
        if (!sauceInputLine.isEmpty()) { // Boş değilse işle
            String[] sauceTypes = sauceInputLine.split(","); // Virgülle ayır
            for (String sauceType : sauceTypes) { // Her bir sos türü için
                String trimmedSauceType = sauceType.trim(); // Boşlukları temizle
                if (!trimmedSauceType.isEmpty()) { // Boş değilse
                    newSandwich.addSauce(trimmedSauceType); // Sosu sandviçe ekle
                    System.out.println(trimmedSauceType + " added."); // Eklendi mesajı
                }
            }
        }

        addSandwich(newSandwich); // Oluşturulan sandviçi siparişe ekle
        System.out.println("Custom sandwich successfully added to order."); // Başarıyla eklendi mesajı
    }


    // Guides the user through adding a drink to the current order.
    private void addDrinkToOrder() {
        System.out.println("\n--- Add Drink ---"); // İçecek ekle başlığı
        System.out.print("Enter drink size (Small, Medium, Large): "); // Boyut iste
        String size = scanner.nextLine();
        System.out.print("Enter drink flavor (e.g., Cola, Sprite): "); // Tat iste
        String flavor = scanner.nextLine();
        addDrink(new Drink(size, flavor)); // İçeceği siparişe ekle
        System.out.println("Drink successfully added to order."); // Başarıyla eklendi mesajı
    }

    // Guides the user through adding chips to the current order.
    private void addChipsToOrder() {
        System.out.println("\n--- Add Chips ---"); // Cips ekle başlığı
        System.out.print("Enter chip type (e.g., Regular, Cheesy): "); // Tür iste
        String type = scanner.nextLine();
        addChip(new Chip(type)); // Cipsi siparişe ekle
        System.out.println("Chips successfully added to order."); // Başarıyla eklendi mesajı
    }

    // Handles the checkout process, displaying order details and saving the receipt.
    private void checkoutOrder() {
        System.out.println("\n--- Order Summary & Checkout ---"); // Ödeme başlığı
        displayOrderDetails(); // Sipariş detaylarını göster

        System.out.print("Confirm order (Y/N) or Cancel (0): "); // Onay veya iptal isteği
        String confirmation = scanner.nextLine().trim().toUpperCase(); // Girdiyi al

        if (confirmation.equals("Y")) {
            ReceiptWriter.saveReceipt(this); // Fişi kaydet (bu Order nesnesini gönder)
            System.out.println("Order successfully completed and receipt generated."); // Başarıyla tamamlandı mesajı
        } else if (confirmation.equals("0")) {
            cancelOrder(); // Siparişi iptal et
        } else {
            System.out.println("Order not confirmed or cancelled. Returning to order menu."); // Onaylanmadı mesajı
        }
    }

    // Cancels the current order.
    private void cancelOrder() {
        this.sandwiches.clear(); // Sandviçleri temizle
        this.drinks.clear(); // İçecekleri temizle
        this.chips.clear(); // Cipsleri temizle
        System.out.println("Order cancelled and cleared."); // İptal edildi mesajı
    }
}