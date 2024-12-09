package org.example;

public class Airplane extends Transport {
    private double wingSpan;   // Размах крыла
    private double maxAltitude; // Максимальная высота

    public Airplane(String brand, double speed, double weight, String color, double wingSpan, double maxAltitude) {
        super(brand, speed, weight, color);
        this.wingSpan = wingSpan;
        this.maxAltitude = maxAltitude;
    }

    public double getWingSpan() {
        return wingSpan;
    }

    public double getMaxAltitude() {
        return maxAltitude;
    }

    @Override
    public String toString() {
        return String.format("Airplane [Brand: %s, Speed: %.2f, Weight: %.2f, Color: %s, WingSpan: %.2f, MaxAltitude: %.2f]",
                getBrand(), getSpeed(), getWeight(), getColor(), wingSpan, maxAltitude);
    }
}

