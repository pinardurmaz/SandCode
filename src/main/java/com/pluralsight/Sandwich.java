package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private int size; // Sandwich size (4, 8, 12 inches) -
    private String breadType; // Type of bread ("white", "wheat", "rye", "wrap")
    private boolean toasted; // Whether the sandwich is toasted
    private List<Topping> meats;
    private List<Topping> cheeses;
    private List<Topping> regularToppings;
    private List<String> sauces;

    public Sandwich(int size, String breadType) { // Constructor for Sandwich class
        this.size = size;
        this.breadType = breadType;
        this.toasted = false; // Default: not toasted
        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
    }

    // Getters for sandwich properties
    public int getSize() { return size; }
    public String getBreadType() { return breadType; }
    public boolean isToasted() { return toasted; }
    public List<Topping> getMeats() { return meats; }
    public List<Topping> getCheeses() { return cheeses; }
    public List<Topping> getRegularToppings() { return regularToppings; }
    public List<String> getSauces() { return sauces; }

    // Setter for toasted property
    public void setToasted(boolean toasted) { this.toasted = toasted; }

    // Methods to add toppings
    public void addMeat(Topping meat) {
        meats.add(meat);
    }
    public void addCheese(Topping cheese) {
        cheeses.add(cheese);
    }
    public void addRegularTopping(Topping topping) {
        regularToppings.add(topping);
    }
    public void addSauce(String sauce) {
        this.sauces.add(sauce);
    }

    // Calculates the total cost of the sandwich
    public double calculateCost() {
        double cost = 0.0;

        // Bread price based on size
        switch (size) {
            case 4: cost += 5.50; break;
            case 8: cost += 7.00; break;
            case 12: cost += 8.50; break;
            default: cost += 0.0; break; // Handle invalid size
        }

        // Add cost of premium toppings (meats and cheeses)
        for (Topping meat : meats) {
            cost += meat.getCost(size);
        }
        for (Topping cheese : cheeses) {
            cost += cheese.getCost(size);
        }

        // Regular toppings and sauces are included and have no extra cost

        return cost;
    }

    // Returns a string representation of the sandwich details
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append(size).append("\" ").append(breadType).append(" Sandwich");
        if (toasted) details.append(" (Toasted)");
        details.append("\n    Meats: ");
        if (meats.isEmpty()) details.append("None");
        else meats.forEach(m -> details.append(m.getName()).append(m.isExtra() ? " (Extra) " : " "));
        details.append("\n    Cheeses: ");
        if (cheeses.isEmpty()) details.append("None");
        else cheeses.forEach(c -> details.append(c.getName()).append(c.isExtra() ? " (Extra) " : " "));
        details.append("\n    Other Toppings: ");
        if (regularToppings.isEmpty()) details.append("None");
        else regularToppings.forEach(t -> details.append(t.getName()).append(" "));
        details.append("\n    Sauces: ");
        if (sauces.isEmpty()) details.append("None");
        else sauces.forEach(s -> details.append(s).append(" "));
        details.append("\n    Price: $").append(String.format("%.2f", calculateCost()));
        return details.toString();
    }
}
