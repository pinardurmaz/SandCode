package com.pluralsight; // Paket bildirimi

public class Drink {
    private String size; // Size of the drink ("Small", "Medium", "Large") - İçeceğin boyutu
    private String flavor; // Flavor of the drink (e.g., "Cola", "Sprite") - İçeceğin tadı

    public Drink(String size, String flavor) { // Constructor for Drink class - İçecek sınıfının yapıcı metodu
        this.size = size; // Boyutu ayarla
        this.flavor = flavor; // Tadı ayarla
    }

    // Getters for drink properties - İçecek özelliklerinin getter metotları
    public String getSize() { return size; }
    public String getFlavor() { return flavor; }

    // Calculates the cost of the drink based on its size - İçeceğin maliyetini boyutuna göre hesaplar
    public double getCost() {
        switch (size.toLowerCase()) { // Boyutu küçük harfe çevirerek karşılaştır
            case "small": return 2.00; // Küçük boyut maliyeti
            case "medium": return 2.50; // Orta boyut maliyeti
            case "large": return 3.00; // Büyük boyut maliyeti
            default: return 0.0; // Handle invalid size - Geçersiz boyut durumu
        }
    }
}
