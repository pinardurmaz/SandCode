package com.pluralsight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// 3. Sandwich.java - Represents a customizable sandwich
public class Sandwich {
    private String size;
    private String bread;
    private List<String> toppings = new ArrayList<>();
    private boolean isToasted;

    private static final List<String> PREMIUM_MEATS = Arrays.asList("steak", "ham", "salami", "roast beef", "chicken", "bacon");
    private static final List<String> PREMIUM_CHEESES = Arrays.asList("american", "provolone", "cheddar", "swiss");

    // Builds sandwich via user input
    public void build(Scanner scanner) {
        System.out.print("Choose sandwich size (4/8/12 inches): ");
        size = scanner.nextLine();

        System.out.print("Choose bread type (white / wheat / rye / wrap): ");
        bread = scanner.nextLine();

        System.out.print("Enter toppings (comma separated): ");
        String[] inputToppings = scanner.nextLine().split(",");
        for (String t : inputToppings) {
            toppings.add(t.trim().toLowerCase());
        }

        System.out.print("Would you like your sandwich toasted? (yes/no): ");
        String toastInput = scanner.nextLine().trim().toLowerCase();
        isToasted = toastInput.equals("yes");
    }

    // Display sandwich info in CLI
    public void print() {
        System.out.println("------------------------------");
        System.out.println("Size     : " + size + "\"");
        System.out.println("Bread    : " + bread);
        System.out.println("Toppings : " + String.join(", ", toppings));
        System.out.println("Toasted  : " + (isToasted ? "Yes" : "No"));
        System.out.printf("Price    : $%.2f\n", getPrice());
        System.out.println("------------------------------");
    }

    // Calculate total price
    public double getPrice() {
        double basePrice = switch (size) {
            case "4" -> 5.50;
            case "8" -> 7.00;
            case "12" -> 8.50;
            default -> 0.0;
        };

        double meatPrice = switch (size) {
            case "4" -> 1.00;
            case "8" -> 2.00;
            case "12" -> 3.00;
            default -> 0.0;
        };

        double cheesePrice = switch (size) {
            case "4" -> 0.75;
            case "8" -> 1.50;
            case "12" -> 2.25;
            default -> 0.0;
        };

        double premiumCost = 0.0;
        for (String topping : toppings) {
            if (PREMIUM_MEATS.contains(topping)) {
                premiumCost += meatPrice;
            } else if (PREMIUM_CHEESES.contains(topping)) {
                premiumCost += cheesePrice;
            }
        }

        return basePrice + premiumCost;
    }

    // Text for receipt
    public String getText() {
        return String.format("Size: %s\"\nBread: %s\nToppings: %s\nToasted: %s\nPrice: $%.2f",
                size, bread, String.join(", ", toppings),
                isToasted ? "Yes" : "No",
                getPrice());
    }

    // Setters for signature sandwiches
    public void setSize(String size) {
        this.size = size;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public void setToasted(boolean toasted) {
        this.isToasted = toasted;
    }

    public void setToppings(String toppingList) {
        this.toppings.clear();
        String[] input = toppingList.split(",");
        for (String t : input) {
            toppings.add(t.trim().toLowerCase());
        }
    }
}