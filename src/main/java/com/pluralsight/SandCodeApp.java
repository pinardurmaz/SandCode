package com.pluralsight;

import java.util.Scanner;

// Entry point of the application
public class SandCodeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Order currentOrder = null;
        boolean isRunning = true;

        ReceiptWriter.createReceiptsFolder();

        printWelcomeScreen();

        while (isRunning) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1) Start New Order");
            System.out.println("0) Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    currentOrder = new Order(scanner);
                    currentOrder.handleOrderFlow();
                    break;
                case "0":
                    isRunning = false;
                    System.out.println("Thank you for using SandCode. Have a great day!");
                    break;
                default:
                    System.out.println("Invalid input. Please enter 1 or 0.");
            }
        }

        scanner.close();
    }

    // Welcome banner
    private static void printWelcomeScreen() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           WELCOME TO SANDCODE        ║");
        System.out.println("║      Your Custom Sandwich Builder    ║");
        System.out.println("║           Powered by Java            ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}
