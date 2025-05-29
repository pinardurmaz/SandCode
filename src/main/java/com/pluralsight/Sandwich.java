package com.pluralsight; // Paket bildirimi

import java.util.ArrayList; // ArrayList sınıfını kullanmak için
import java.util.List; // List arayüzünü kullanmak için

public class Sandwich {
    private int size; // Sandwich size (4, 8, 12 inches) - Sandviç boyutu (4, 8, 12 inç)
    private String breadType; // Type of bread ("white", "wheat", "rye", "wrap") - Ekmek türü
    private boolean toasted; // Whether the sandwich is toasted - Sandviçin kızartılıp kızartılmadığı
    private List<Topping> meats; // List of meat toppings - Et toppinglerinin listesi
    private List<Topping> cheeses; // List of cheese toppings - Peynir toppinglerinin listesi
    private List<Topping> regularToppings; // List of regular toppings - Normal toppinglerin listesi
    private List<String> sauces; // List of sauces - Sosların listesi

    public Sandwich(int size, String breadType) { // Constructor for Sandwich class - Sandviç sınıfının yapıcı metodu
        this.size = size; // Boyutu ayarla
        this.breadType = breadType; // Ekmek türünü ayarla
        this.toasted = false; // Default: not toasted - Varsayılan: kızartılmamış
        this.meats = new ArrayList<>(); // Et listesini başlat
        this.cheeses = new ArrayList<>(); // Peynir listesini başlat
        this.regularToppings = new ArrayList<>(); // Normal topping listesini başlat
        this.sauces = new ArrayList<>(); // Sos listesini başlat
    }

    // Getters for sandwich properties - Sandviç özelliklerinin getter metotları
    public int getSize() { return size; }
    public String getBreadType() { return breadType; }
    public boolean isToasted() { return toasted; }
    public List<Topping> getMeats() { return meats; }
    public List<Topping> getCheeses() { return cheeses; }
    public List<Topping> getRegularToppings() { return regularToppings; }
    public List<String> getSauces() { return sauces; }

    // Setter for toasted property - Kızartma özelliğinin setter metodu
    public void setToasted(boolean toasted) { this.toasted = toasted; }

    // Methods to add toppings - Topping ekleme metotları
    public void addMeat(Topping meat) {
        meats.add(meat); // Eti listeye ekle
    }
    public void addCheese(Topping cheese) {
        cheeses.add(cheese); // Peyniri listeye ekle
    }
    public void addRegularTopping(Topping topping) {
        regularToppings.add(topping); // Normal toppingi listeye ekle
    }
    public void addSauce(String sauce) {
        this.sauces.add(sauce); // Sosu listeye ekle
    }

    // Calculates the total cost of the sandwich - Sandviçin toplam maliyetini hesaplar
    public double calculateCost() {
        double cost = 0.0; // Maliyeti sıfırla

        // Bread price based on size (assuming all bread types have the same base price)
        // Ekmek fiyatı boyuta göre (tüm ekmek türlerinin aynı taban fiyata sahip olduğu varsayılıyor)
        switch (size) {
            case 4: cost += 5.50; break;
            case 8: cost += 7.00; break;
            case 12: cost += 8.50; break;
            default: cost += 0.0; break; // Handle invalid size - Geçersiz boyut durumu
        }

        // Add cost of premium toppings (meats and cheeses)
        // Premium toppinglerin (etler ve peynirler) maliyetini ekle
        for (Topping meat : meats) {
            cost += meat.getCost(size); // Topping sınıfındaki getCost metodunu çağır
        }
        for (Topping cheese : cheeses) {
            cost += cheese.getCost(size); // Topping sınıfındaki getCost metodunu çağır
        }

        // Regular toppings and sauces are included and have no extra cost
        // Normal toppingler ve soslar fiyata dahil olup ekstra maliyeti yoktur

        return cost; // Toplam maliyeti döndür
    }

    // Returns a string representation of the sandwich details - Sandviç detaylarının string temsilini döndürür
    public String getDetails() {
        StringBuilder details = new StringBuilder(); // Detaylar için StringBuilder
        details.append(size).append("\" ").append(breadType).append(" Sandwich"); // Boyut ve ekmek türünü ekle
        if (toasted) details.append(" (Toasted)"); // Kızartılmışsa belirt
        details.append("\n    Meats: "); // Etler başlığı
        if (meats.isEmpty()) details.append("None"); // Et yoksa
        else meats.forEach(m -> details.append(m.getName()).append(m.isExtra() ? " (Extra) " : " ")); // Her eti ve ekstra olup olmadığını ekle
        details.append("\n    Cheeses: "); // Peynirler başlığı
        if (cheeses.isEmpty()) details.append("None"); // Peynir yoksa
        else cheeses.forEach(c -> details.append(c.getName()).append(c.isExtra() ? " (Extra) " : " ")); // Her peyniri ve ekstra olup olmadığını ekle
        details.append("\n    Other Toppings: "); // Diğer toppingler başlığı
        if (regularToppings.isEmpty()) details.append("None"); // Normal topping yoksa
        else regularToppings.forEach(t -> details.append(t.getName()).append(" ")); // Her normal toppingi ekle
        details.append("\n    Sauces: "); // Soslar başlığı
        if (sauces.isEmpty()) details.append("None"); // Sos yoksa
        else sauces.forEach(s -> details.append(s).append(" ")); // Her sosu ekle
        details.append("\n    Price: $").append(String.format("%.2f", calculateCost())); // Fiyatı ekle
        return details.toString(); // Detayları döndür
    }
}
