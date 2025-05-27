package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents a customizable sandwich
public class Sandwich {
    private String size;
    private String bread;
    private List<String> toppings = new ArrayList<>();

    // Prompts the user to build a sandwich step by step
    public void build(Scanner scanner) {
        System.out.print("Choose sandwich size (4/8/12 inches): ");
        size = scanner.nextLine();

        System.out.print("Choose bread type (white / wheat / rye / wrap): ");
        bread = scanner.nextLine();

        System.out.print("Enter toppings (comma separated): ");
        String[] inputToppings = scanner.nextLine().split(",");
        for (String t : inputToppings) {
            toppings.add(t.trim());
        }
    }

    // Displays sandwich details
    public void print() {
        System.out.println("------------------------------");
        System.out.println("Size: " + size + "\"");
        System.out.println("Bread: " + bread);
        System.out.println("Toppings: " + String.join(", ", toppings));
        System.out.printf("Price: $%.2f\n", getPrice());
        System.out.println("------------------------------");
    }

    // Calculates sandwich price based on size and number of toppings
    public double getPrice() {
        double basePrice = switch (size) {
            case "4" -> 5.50;
            case "8" -> 7.00;
            case "12" -> 8.50;
            default -> 0.0;
        };

        return basePrice + (toppings.size() * 0.50); // $0.50 per topping
    }
}