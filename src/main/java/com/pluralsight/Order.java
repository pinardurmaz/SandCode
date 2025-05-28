package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 2. Order.java - Handles full order: sandwich, drink, chips, checkout, receipt
public class Order {
    private List<Sandwich> sandwiches = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Chip> chips = new ArrayList<>();

    // Menu-driven order flow
    public void orderMenu(Scanner scanner) {
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n=== ORDER MENU ===");
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("5) Add Signature Sandwich");
            System.out.println("0) Cancel Order");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Sandwich sandwich = new Sandwich();
                    sandwich.build(scanner);
                    sandwiches.add(sandwich);
                    break;
                case "2":
                    addDrink(scanner);
                    break;
                case "3":
                    addChips(scanner);
                    break;
                case "4":
                    printSummary();
                    ReceiptWriter.write(this);
                    ordering = false;
                    break;
                case "5":
                    addSignatureSandwich(scanner);
                    break;
                case "0":
                    System.out.println("Order canceled.");
                    sandwiches.clear();
                    drinks.clear();
                    chips.clear();
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void addDrink(Scanner scanner) {
        System.out.print("Enter drink flavor: ");
        String flavor = scanner.nextLine();

        System.out.print("Enter drink size (small / medium / large): ");
        String size = scanner.nextLine();

        drinks.add(new Drink(size, flavor));
    }

    private void addChips(Scanner scanner) {
        System.out.print("Enter chip type: ");
        String type = scanner.nextLine();
        chips.add(new Chip(type));
    }

    private void addSignatureSandwich(Scanner scanner) {
        System.out.println("Choose a signature sandwich:");
        System.out.println("1) BLT");
        System.out.println("2) Philly Cheese Steak");
        String sigChoice = scanner.nextLine();

        if (sigChoice.equals("1")) {
            sandwiches.add(SignatureSandwich.createBLT());
        } else if (sigChoice.equals("2")) {
            sandwiches.add(SignatureSandwich.createPhilly());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // Displays full summary
    public void printSummary() {
        System.out.println("\n=== ORDER SUMMARY ===");
        double total = 0;

        for (Sandwich s : sandwiches) {
            s.print();
            total += s.getPrice();
        }

        for (Drink d : drinks) {
            d.print();
            total += d.getPrice();
        }

        for (Chip c : chips) {
            c.print();
            total += c.getPrice();
        }

        System.out.printf("\nTOTAL PRICE: $%.2f\n", total);
    }

    // For writing receipt text
    public String getReceiptText() {
        StringBuilder sb = new StringBuilder();
        double total = 0;

        for (Sandwich s : sandwiches) {
            sb.append("Sandwich:\n");
            sb.append(s.getText());
            total += s.getPrice();
            sb.append("\n");
        }

        for (Drink d : drinks) {
            sb.append(String.format("Drink: %s (%s) - $%.2f\n", d.getFlavor(), d.getSize(), d.getPrice()));
            total += d.getPrice();
        }

        for (Chip c : chips) {
            sb.append(String.format("Chips: %s - $%.2f\n", c.getType(), c.getPrice()));
            total += c.getPrice();
        }

        sb.append(String.format("\nTOTAL: $%.2f\n", total));
        return sb.toString();
    }
}