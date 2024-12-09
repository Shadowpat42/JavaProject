package org.example;

public class Car extends Transport {

    public Car(String brand, double speed, double weight, String color) {
        super(brand, speed, weight, color);
    }

    @Override
    public String toString() {
        return String.format("Car [Brand: %s, Speed: %.2f, Weight: %.2f, Color: %s]",
                getBrand(), getSpeed(), getWeight(), getColor());
    }
}

