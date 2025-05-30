package com.pluralsight;

public class SignatureSandwich extends Sandwich {

    // Constructor for SignatureSandwich. Sets up a predefined sandwich.
    public SignatureSandwich(String name, int size, String breadType, boolean toasted) {
        super(size, breadType);
        this.setToasted(toasted);
    }

    // Factory method to create a BLT signature sandwich.
    public static SignatureSandwich createBLT(int size) {
        SignatureSandwich blt = new SignatureSandwich("BLT", size, "white", true);
        blt.addMeat(new Topping("Bacon", true, false));
        blt.addCheese(new Topping("Cheddar", true, false));
        blt.addRegularTopping(new Topping("Lettuce", false, false));
        blt.addRegularTopping(new Topping("Tomato", false, false));
        blt.addSauce("Ranch");
        return blt;
    }

    // Factory method to create a Philly Cheese Steak signature sandwich.
    public static SignatureSandwich createPhillyCheeseSteak(int size) {
        SignatureSandwich philly = new SignatureSandwich("Philly Cheese Steak", size, "white", true);
        philly.addMeat(new Topping("Steak", true, false));
        philly.addCheese(new Topping("American", true, false));
        philly.addRegularTopping(new Topping("Peppers", false, false));
        philly.addSauce("Mayo");
        return philly;
    }
}