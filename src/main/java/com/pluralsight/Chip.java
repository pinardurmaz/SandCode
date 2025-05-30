package com.pluralsight;

public class Chip {
    private String type; // Type of chips (e.g., "Regular", "Cheesy")

    public Chip(String type) { // Constructor for Chip class
        this.type = type;
    }

    // Getter for chip type
    public String getType() { return type; }

    // Calculates the cost of the chips (sabit)
    public double getCost() {
        return 1.50;
    }
}