package com.pluralsight; // Paket bildirimi

public class SignatureSandwich extends Sandwich { // Sandwich sınıfından miras alır

    // Constructor for SignatureSandwich. Sets up a predefined sandwich.
    // SignatureSandwich için yapıcı metot. Önceden tanımlanmış bir sandviç ayarlar.
    public SignatureSandwich(String name, int size, String breadType, boolean toasted) {
        super(size, breadType); // Üst sınıfın (Sandwich) yapıcı metodunu çağır
        this.setToasted(toasted); // Kızartma durumunu ayarla
        // Note: 'name' is just for identification here, not stored in Sandwich directly
        // Not: 'name' burada sadece tanımlama içindir, doğrudan Sandwich'te saklanmaz
    }

    // Factory method to create a BLT signature sandwich.
    // BLT imzalı sandviç oluşturmak için fabrika metodu.
    public static SignatureSandwich createBLT(int size) {
        SignatureSandwich blt = new SignatureSandwich("BLT", size, "white", true); // BLT sandviçini oluştur
        blt.addMeat(new Topping("Bacon", true, false)); // Bacon ekle
        blt.addCheese(new Topping("Cheddar", true, false)); // Cheddar ekle
        blt.addRegularTopping(new Topping("Lettuce", false, false)); // Marul ekle
        blt.addRegularTopping(new Topping("Tomato", false, false)); // Domates ekle
        blt.addSauce("Ranch"); // Ranch sosu ekle
        return blt; // BLT sandviçini döndür
    }

    // Factory method to create a Philly Cheese Steak signature sandwich.
    // Philly Cheese Steak imzalı sandviç oluşturmak için fabrika metodu.
    public static SignatureSandwich createPhillyCheeseSteak(int size) {
        SignatureSandwich philly = new SignatureSandwich("Philly Cheese Steak", size, "white", true); // Philly Cheese Steak sandviçini oluştur
        philly.addMeat(new Topping("Steak", true, false)); // Steak ekle
        philly.addCheese(new Topping("American", true, false)); // American Cheese ekle
        philly.addRegularTopping(new Topping("Peppers", false, false)); // Biber ekle
        philly.addSauce("Mayo"); // Mayo sosu ekle
        return philly; // Philly Cheese Steak sandviçini döndür
    }
}