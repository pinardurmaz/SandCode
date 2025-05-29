package com.pluralsight; // Paket bildirimi

public class Topping {
    private String name; // Name of the topping (e.g., "steak", "cheddar", "lettuce") - Toppingin adı
    private boolean isPremium; // True if it's a premium topping (meat/cheese), false otherwise - Premium topping olup olmadığı (et/peynir ise true)
    private boolean isExtra; // True if the topping is added as extra - Toppingin ekstra eklenip eklenmediği

    public Topping(String name, boolean isPremium, boolean isExtra) { // Constructor for Topping class - Topping sınıfının yapıcı metodu
        this.name = name; // Adı ayarla
        this.isPremium = isPremium; // Premium durumunu ayarla
        this.isExtra = isExtra; // Ekstra durumunu ayarla
    }

    // Getters for topping properties - Topping özelliklerinin getter metotları
    public String getName() { return name; }
    public boolean isPremium() { return isPremium; }
    public boolean isExtra() { return isExtra; }

    // Calculates the cost of the topping based on sandwich size and whether it's extra.
    // Toppingin maliyetini sandviç boyutuna ve ekstra olup olmadığına göre hesaplar.
    public double getCost(int size) {
        if (!isPremium) {
            return 0.0; // Regular toppings and sauces are free - Normal toppingler ve soslar ücretsiz
        }

        double baseCost = 0.0; // Temel maliyet
        double extraCost = 0.0; // Ekstra maliyet

        // Determine base and extra cost for meats - Etler için temel ve ekstra maliyeti belirle
        if (name.equalsIgnoreCase("steak") || name.equalsIgnoreCase("ham") ||
                name.equalsIgnoreCase("salami") || name.equalsIgnoreCase("roast beef") ||
                name.equalsIgnoreCase("chicken") || name.equalsIgnoreCase("bacon")) {
            switch (size) {
                case 4: baseCost = 1.00; extraCost = 0.50; break;
                case 8: baseCost = 2.00; extraCost = 1.00; break;
                case 12: baseCost = 3.00; extraCost = 1.50; break;
            }
        }
        // Determine base and extra cost for cheeses - Peynirler için temel ve ekstra maliyeti belirle
        else if (name.equalsIgnoreCase("american") || name.equalsIgnoreCase("provolone") ||
                name.equalsIgnoreCase("cheddar") || name.equalsIgnoreCase("swiss")) {
            switch (size) {
                case 4: baseCost = 0.75; extraCost = 0.30; break;
                case 8: baseCost = 1.50; extraCost = 0.60; break;
                case 12: baseCost = 2.25; extraCost = 0.90; break;
            }
        }
        // Add more premium toppings here if needed - Gerekirse buraya daha fazla premium topping ekle

        return baseCost + (isExtra ? extraCost : 0.0); // Temel maliyete ekstra maliyeti ekle (eğer ekstra ise)
    }
}