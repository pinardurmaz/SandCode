package com.pluralsight; // Paket bildirimi

import java.util.Scanner; // Scanner sınıfını kullanmak için

// Entry point of the application
public class SandCodeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Kullanıcıdan girdi almak için Scanner nesnesi
        Order currentOrder = null; // Mevcut siparişi tutacak
        boolean isRunning = true; // Uygulamanın çalışıp çalışmadığını kontrol eden bayrak

        // Uygulama başladığında fiş klasörünü oluşturmayı dene
        ReceiptWriter.createReceiptsFolder();

        printWelcomeScreen(); // Karşılama ekranını göster

        while (isRunning) { // Uygulama çalıştığı sürece döngü devam eder
            System.out.println("\n=== MAIN MENU ==="); // Ana menü başlığı
            System.out.println("1) Start New Order"); // Yeni sipariş başlat seçeneği
            System.out.println("0) Exit"); // Çıkış seçeneği
            System.out.print("Enter your choice: "); // Kullanıcıdan seçim iste
            String choice = scanner.nextLine(); // Kullanıcının seçimini al

            switch (choice) { // Seçime göre işlem yap
                case "1":
                    currentOrder = new Order(scanner); // Yeni bir sipariş nesnesi oluştur, scanner'ı Order'a geç
                    currentOrder.handleOrderFlow(); // Sipariş akışını başlat (Order sınıfındaki metot)
                    break;
                case "0":
                    isRunning = false; // Döngüden çıkmak için bayrağı false yap
                    System.out.println("Thank you for using SandCode. Have a great day!"); // Çıkış mesajı
                    break;
                default:
                    System.out.println("Invalid input. Please enter 1 or 0."); // Geçersiz giriş mesajı
            }
        }

        scanner.close(); // Uygulama kapanırken scanner'ı kapat
    }

    // Welcome banner
    // Karşılama ekranı başlığı
    private static void printWelcomeScreen() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           WELCOME TO SANDCODE        ║");
        System.out.println("║      Your Custom Sandwich Builder    ║");
        System.out.println("║           Powered by Java            ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}
