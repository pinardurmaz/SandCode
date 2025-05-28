package com.pluralsight;

// 7. SignatureSandwich.java - Provides ready-made sandwich options
public class SignatureSandwich extends Sandwich {

    public static Sandwich createBLT() {
        Sandwich blt = new Sandwich();
        blt.setSize("8");
        blt.setBread("white");
        blt.setToppings("bacon, cheddar, lettuce, tomato, ranch");
        blt.setToasted(true);
        return blt;
    }

    public static Sandwich createPhilly() {
        Sandwich philly = new Sandwich();
        philly.setSize("8");
        philly.setBread("white");
        philly.setToppings("steak, american, peppers, mayo");
        philly.setToasted(true);
        return philly;
    }
}