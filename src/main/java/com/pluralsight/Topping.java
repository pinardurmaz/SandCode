package com.pluralsight;

public class Topping {
    private String name; // Name of the topping (e.g., "steak", "cheddar", "lettuce")
    private boolean isPremium; // True if it's a premium topping (meat/cheese), false otherwise -
    private boolean isExtra; // True if the topping is added as extra

    public Topping(String name, boolean isPremium, boolean isExtra) { // Constructor for Topping class
        this.name = name;
        this.isPremium = isPremium;
        this.isExtra = isExtra;
    }

    // Getters for topping properties
    public String getName() { return name; }
    public boolean isPremium() { return isPremium; }
    public boolean isExtra() { return isExtra; }

    // Calculates the cost of the topping based on sandwich size and whether it's extra.
    public double getCost(int size) {
        if (!isPremium) {
            return 0.0; // Regular toppings and sauces are free
        }

        double baseCost = 0.0;
        double extraCost = 0.0;

        // Determine base and extra cost for meats
        if (name.equalsIgnoreCase("steak") || name.equalsIgnoreCase("ham") ||
                name.equalsIgnoreCase("salami") || name.equalsIgnoreCase("roast beef") ||
                name.equalsIgnoreCase("chicken") || name.equalsIgnoreCase("bacon")) {
            switch (size) {
                case 4: baseCost = 1.00; extraCost = 0.50; break;
                case 8: baseCost = 2.00; extraCost = 1.00; break;
                case 12: baseCost = 3.00; extraCost = 1.50; break;
            }
        }
        // Determine base and extra cost for cheeses
        else if (name.equalsIgnoreCase("american") || name.equalsIgnoreCase("provolone") ||
                name.equalsIgnoreCase("cheddar") || name.equalsIgnoreCase("swiss")) {
            switch (size) {
                case 4: baseCost = 0.75; extraCost = 0.30; break;
                case 8: baseCost = 1.50; extraCost = 0.60; break;
                case 12: baseCost = 2.25; extraCost = 0.90; break;
            }
        }
        // Add more premium toppings here if needed

        return baseCost + (isExtra ? extraCost : 0.0);
    }
}