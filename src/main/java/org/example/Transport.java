package org.example;

public abstract class Transport {
    private String brand;
    private double speed;
    private double weight;
    private String color;  // Цвет хранится как строка в формате #RRGGBB

    public Transport(String brand, double speed, double weight, String color) {
        this.brand = brand;
        this.speed = speed;
        this.weight = weight;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public double getSpeed() {
        return speed;
    }

    public double getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("Transport [Brand: %s, Speed: %.2f, Weight: %.2f, Color: %s]",
                brand, speed, weight, color);
    }
}




