package com.pluralsight; // Paket bildirimi

public class Chip {
    private String type; // Type of chips (e.g., "Regular", "Cheesy") - Cips türü

    public Chip(String type) { // Constructor for Chip class - Cips sınıfının yapıcı metodu
        this.type = type; // Türü ayarla
    }

    // Getter for chip type - Cips türünün getter metodu
    public String getType() { return type; }

    // Calculates the cost of the chips (fixed price) - Cipsin maliyetini hesaplar (sabit fiyat)
    public double getCost() {
        return 1.50; // Fixed price for chips - Cipsler için sabit fiyat
    }
}