package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order {
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chip> chips;
    private Scanner scanner;

    public Order(Scanner scanner) {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
        this.scanner = scanner;
    }

    // Adds a sandwich to the order.
    public void addSandwich(Sandwich sandwich) {
        this.sandwiches.add(sandwich);
    }

    // Adds a drink to the order.
    public void addDrink(Drink drink) {
        this.drinks.add(drink);
    }

    // Adds chips to the order.
    public void addChip(Chip chip) {
        this.chips.add(chip);
    }

    // Calculates the total cost of the order.
    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Sandwich s : sandwiches) {
            totalCost += s.calculateCost();
        }
        for (Drink d : drinks) {
            totalCost += d.getCost();
        }
        for (Chip c : chips) {
            totalCost += c.getCost();
        }
        return totalCost;
    }

    // Displays the detailed order summary to the console in a receipt-like format.
    public void displayOrderDetails() {
        System.out.println("\n====================================");
        System.out.println("          SandCode Order             ");
        System.out.println("====================================");

        if (sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty()) {
            System.out.println("Your order is currently empty.");
            System.out.println("====================================");
            return;
        }

        System.out.println("\n--- SANDWICHES ---");
        if (sandwiches.isEmpty()) {
            System.out.println("  No sandwiches added.");
        } else {
            for (int i = 0; i < sandwiches.size(); i++) {
                System.out.println("  #" + (i + 1) + " Sandwich Details:");
                String[] sandwichDetails = sandwiches.get(i).getDetails().split("\n");
                for (String detailLine : sandwichDetails) {
                    System.out.println("  " + detailLine);
                }
                System.out.println("  --------------------");
            }
        }

        System.out.println("\n--- DRINKS ---");
        if (drinks.isEmpty()) {
            System.out.println("  No drinks added.");
        } else {
            for (Drink d : drinks) {
                System.out.printf("  - %-15s %-10s $%.2f\n", d.getSize(), d.getFlavor(), d.getCost());
            }
        }

        System.out.println("\n--- CHIPS ---");
        if (chips.isEmpty()) {
            System.out.println("  No chips added.");
            for (Chip c : chips) {
                System.out.printf("  - %-25s $%.2f\n", c.getType(), c.getCost());
            }
        }

        System.out.println("\n====================================");
        System.out.printf("TOTAL AMOUNT:             $%.2f\n", calculateTotalCost());
        System.out.println("====================================");
        System.out.println("  Thank you for your order!         ");
        System.out.println("====================================");
    }

    // Generates the receipt content as a String.
    public String generateReceiptContent() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("====================================\n");
        receipt.append("          SandCode Receipt        \n");
        receipt.append("====================================\n");
        receipt.append("Date: ").append(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        receipt.append("------------------------------------\n");
        receipt.append("Order Details:\n");

        if (!sandwiches.isEmpty()) {
            receipt.append("\n--- SANDWICHES ---\n");
            for (Sandwich s : sandwiches) {
                String[] sandwichDetails = s.getDetails().split("\n");
                for (String detailLine : sandwichDetails) {
                    receipt.append("  ").append(detailLine).append("\n");
                }
                receipt.append("  --------------------\n");
            }
        }
        if (!drinks.isEmpty()) {
            receipt.append("\n--- DRINKS ---\n");
            for (Drink d : drinks) {
                receipt.append(String.format("  - %-15s %-10s $%.2f\n", d.getSize(), d.getFlavor(), d.getCost()));
            }
        }
        if (!chips.isEmpty()) {
            receipt.append("\n--- CHIPS ---\n");
            for (Chip c : chips) {
                receipt.append(String.format("  - %-25s $%.2f\n", c.getType(), c.getCost()));
            }
        }

        receipt.append("\n====================================\n");
        receipt.append(String.format("TOTAL AMOUNT:             $%.2f\n", calculateTotalCost()));
        receipt.append("====================================\n");
        receipt.append("  Thank you for your order!         \n");
        receipt.append("====================================\n");
        return receipt.toString();
    }

    // Handles the flow of adding items to the order.
    public void handleOrderFlow() {
        int orderChoice;
        do {
            displayOrderMenu();
            orderChoice = getUserChoice();

            switch (orderChoice) {
                case 1:
                    addSandwichToOrder();
                    break;
                case 2:
                    addDrinkToOrder();
                    break;
                case 3:
                    addChipsToOrder();
                    break;
                case 4:
                    checkoutOrder();
                    orderChoice = 0;
                    break;
                case 0:
                    cancelOrder();
                    orderChoice = 0;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (orderChoice != 0);
    }

    // Displays the order menu options.
    private void displayOrderMenu() {
        System.out.println("\n--- Order Menu ---");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        System.out.print("Enter your choice: ");
    }

    // Gets integer input from the user, handling invalid inputs.
    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print("Enter your choice: ");
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    // Guides the user through adding a sandwich to the current order.
    private void addSandwichToOrder() {
        System.out.println("\n--- Add Sandwich ---");
        System.out.println("1) Choose a Signature Sandwich");
        System.out.println("2) Build a Custom Sandwich");
        System.out.print("Enter your choice: ");

        int sandwichTypeChoice = getUserChoice();

        if (sandwichTypeChoice == 1) {
            addSignatureSandwich();
        } else if (sandwichTypeChoice == 2) {
            addCustomSandwich();
        } else {
            System.out.println("Invalid choice. Returning to order menu.");
        }
    }

    // Guides the user through adding a signature sandwich.
    private void addSignatureSandwich() {
        System.out.println("\n--- Choose Signature Sandwich ---");
        System.out.println("1) BLT (White Bread, Bacon, Cheddar, Lettuce, Tomato, Ranch, Toasted)");
        System.out.println("2) Philly Cheese Steak (White Bread, Steak, American Cheese, Peppers, Mayo, Toasted)");
        System.out.print("Enter your choice: ");

        int signatureChoice = getUserChoice();
        int size = 8; // Signature sandwiches are typically 8"

        Sandwich signatureSandwich = null;
        switch (signatureChoice) {
            case 1:
                signatureSandwich = SignatureSandwich.createBLT(size);
                break;
            case 2:
                signatureSandwich = SignatureSandwich.createPhillyCheeseSteak(size);
            default:
                System.out.println("Invalid signature sandwich choice.");
                return;
        }

        addSandwich(signatureSandwich);
        System.out.println(signatureSandwich.getBreadType() + " " + signatureSandwich.getSize() + "\" " + "Signature Sandwich added to order.");
    }

    // Guides the user through building a custom sandwich, allowing comma-separated inputs for toppings.
    private void addCustomSandwich() {
        System.out.println("\n--- Build Custom Sandwich ---");

        System.out.print("Select your bread (white, wheat, rye, wrap): ");
        String bread = scanner.nextLine();

        int size = 0;
        boolean validSize = false;
        while (!validSize) {
            System.out.print("Select sandwich size (4, 8, 12): ");
            size = getUserChoice();
            if (size == 4 || size == 8 || size == 12) {
                validSize = true;
            } else {
                System.out.println("Invalid size. Please choose 4, 8, or 12.");
            }
        }

        Sandwich newSandwich = new Sandwich(size, bread);

        System.out.print("Would you like the sandwich toasted (Y/N)? ");
        String toastedInput = scanner.nextLine().trim().toUpperCase();
        newSandwich.setToasted(toastedInput.equals("Y"));

        // Add Meats
        System.out.println("\n--- Add Meats ---");
        System.out.println("Available: steak, ham, salami, roast beef, chicken, bacon");
        System.out.print("Enter meat types (comma-separated, leave blank for none): ");
        String meatInputLine = scanner.nextLine().trim();
        if (!meatInputLine.isEmpty()) {
            String[] meatTypes = meatInputLine.split(",");
            for (String meatType : meatTypes) {
                String trimmedMeatType = meatType.trim();
                if (!trimmedMeatType.isEmpty()) {
                    System.out.print("Add extra " + trimmedMeatType + " (Y/N)? ");
                    boolean isExtraMeat = scanner.nextLine().trim().toUpperCase().equals("Y");
                    newSandwich.addMeat(new Topping(trimmedMeatType, true, isExtraMeat));
                    System.out.println(trimmedMeatType + " added.");
                }
            }
        }

        // Add Cheeses
        System.out.println("\n--- Add Cheeses ---");
        System.out.println("Available: american, provolone, cheddar, swiss");
        System.out.print("Enter cheese types (comma-separated, leave blank for none): ");
        String cheeseInputLine = scanner.nextLine().trim();
        if (!cheeseInputLine.isEmpty()) {
            String[] cheeseTypes = cheeseInputLine.split(",");
            for (String cheeseType : cheeseTypes) {
                String trimmedCheeseType = cheeseType.trim();
                if (!trimmedCheeseType.isEmpty()) {
                    System.out.print("Add extra " + trimmedCheeseType + " (Y/N)? ");
                    boolean isExtraCheese = scanner.nextLine().trim().toUpperCase().equals("Y");
                    newSandwich.addCheese(new Topping(trimmedCheeseType, true, isExtraCheese));
                    System.out.println(trimmedCheeseType + " added.");
                }
            }
        }

        // Add Regular Toppings
        System.out.println("\n--- Add Regular Toppings ---");
        System.out.println("Available: lettuce, peppers, onions, tomatoes, jalapeños, cucumbers, pickles, guacamole, mushrooms");
        System.out.print("Enter toppings (comma-separated, leave blank for none): ");
        String regularToppingInputLine = scanner.nextLine().trim();
        if (!regularToppingInputLine.isEmpty()) {
            String[] regularToppingTypes = regularToppingInputLine.split(",");
            for (String toppingType : regularToppingTypes) {
                String trimmedToppingType = toppingType.trim();
                if (!trimmedToppingType.isEmpty()) {
                    newSandwich.addRegularTopping(new Topping(trimmedToppingType, false, false));
                    System.out.println(trimmedToppingType + " added.");
                }
            }
        }

        // Add Sauces
        System.out.println("\n--- Add Sauces ---");
        System.out.println("Available: mayo, mustard, ketchup, ranch, thousand islands, vinaigrette, au jus");
        System.out.print("Enter sauces (comma-separated, leave blank for none): ");
        String sauceInputLine = scanner.nextLine().trim();
        if (!sauceInputLine.isEmpty()) {
            String[] sauceTypes = sauceInputLine.split(",");
            for (String sauceType : sauceTypes) {
                String trimmedSauceType = sauceType.trim();
                if (!trimmedSauceType.isEmpty()) {
                    newSandwich.addSauce(trimmedSauceType);
                    System.out.println(trimmedSauceType + " added.");
                }
            }
        }

        addSandwich(newSandwich);
        System.out.println("Custom sandwich successfully added to order.");
    }


    // Guides the user through adding a drink to the current order.
    private void addDrinkToOrder() {
        System.out.println("\n--- Add Drink ---"); //
        System.out.print("Enter drink size (Small, Medium, Large): ");
        String size = scanner.nextLine();
        System.out.print("Enter drink flavor (e.g., Cola, Sprite): ");
        String flavor = scanner.nextLine();
        addDrink(new Drink(size, flavor));
        System.out.println("Drink successfully added to order.");
    }

    // Guides the user through adding chips to the current order.
    private void addChipsToOrder() {
        System.out.println("\n--- Add Chips ---");
        System.out.print("Enter chip type (e.g., Regular, Cheesy): ");
        String type = scanner.nextLine();
        addChip(new Chip(type));
        System.out.println("Chips successfully added to order.");
    }

    // Handles the checkout process, displaying order details and saving the receipt.
    private void checkoutOrder() {
        System.out.println("\n--- Order Summary & Checkout ---");
        displayOrderDetails();

        System.out.print("Confirm order (Y/N) or Cancel (0): ");
        String confirmation = scanner.nextLine().trim().toUpperCase();

        if (confirmation.equals("Y")) {
            ReceiptWriter.saveReceipt(this);
            System.out.println("Order successfully completed and receipt generated.");
        } else if (confirmation.equals("0")) {
            cancelOrder();
        } else {
            System.out.println("Order not confirmed or cancelled. Returning to order menu.");
        }
    }

    // Cancels the current order.
    private void cancelOrder() {
        this.sandwiches.clear();
        this.drinks.clear();
        this.chips.clear();
        System.out.println("Order cancelled and cleared.");
    }
}