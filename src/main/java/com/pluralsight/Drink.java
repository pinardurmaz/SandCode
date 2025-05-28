package com.pluralsight;

// 4. Drink.java - Represents a drink with size and flavor
public class Drink {
    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size.toLowerCase();
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    public double getPrice() {
        return switch (size) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.0;
        };
    }

    public void print() {
        System.out.printf("Drink: %s (%s) - $%.2f\n", flavor, size, getPrice());
    }
}