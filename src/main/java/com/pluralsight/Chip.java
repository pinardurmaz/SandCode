package com.pluralsight;

// 5. Chip.java - Represents chips with type and fixed price
public class Chip {
    private String type;

    public Chip(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return 1.50;
    }

    public void print() {
        System.out.printf("Chips: %s - $%.2f\n", type, getPrice());
    }
}