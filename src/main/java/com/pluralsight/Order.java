package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Manages a list of sandwiches in an order
public class Order {
    private List<Sandwich> sandwiches = new ArrayList<>();

    // Builds the order by letting the user create multiple sandwiches
    public void create(Scanner scanner) {
        System.out.print("How many sandwiches would you like to order? ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= count; i++) {
            System.out.println("\n--- Sandwich #" + i + " ---");
            Sandwich sandwich = new Sandwich();
            sandwich.build(scanner);
            sandwiches.add(sandwich);
        }
    }

    // Displays all sandwiches and calculates the total price
    public void printSummary() {
        System.out.println("\n=== ORDER SUMMARY ===");
        double total = 0;
        for (Sandwich s : sandwiches) {
            s.print();
            total += s.getPrice();
        }
        System.out.printf("Total Price: $%.2f\n", total);
    }
}