package com.pluralsight;

public class Drink {
    private String size;
    private String flavor;

    public Drink(String size, String flavor) { // Constructor for Drink class
        this.size = size;
        this.flavor = flavor;
    }

    // Getters for drink properties
    public String getSize() { return size; }
    public String getFlavor() { return flavor; }

    // Calculates the cost of the drink based on its size
    public double getCost() {
        switch (size.toLowerCase()) {
            case "small": return 2.00;
            case "medium": return 2.50;
            case "large": return 3.00;
            default: return 0.0; // Handle invalid size
        }
    }
}
