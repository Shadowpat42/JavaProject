package org.example;

import java.util.ArrayList;
import java.util.List;

public class TransportHandler {
    private List<Transport> transports;

    public TransportHandler() {
        transports = new ArrayList<>();
    }

    // Генерация случайных объектов транспорта
    public void generateTransports(int count) {
        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                transports.add(generateAirplane());
            } else {
                transports.add(generateCar());
            }
        }
    }

    private Airplane generateAirplane() {
        String brand = "AirBrand" + (int)(Math.random() * 100);
        double speed = 200 + Math.random() * 600;  // 200-800 км/ч
        double weight = 5000 + Math.random() * 20000;  // 5000-25000 кг
        int r = (int)(Math.random() * 256);
        int g = (int)(Math.random() * 256);
        int b = (int)(Math.random() * 256);
        String colorString = String.format("#%02X%02X%02X", r, g, b);
        double wingSpan = 10 + Math.random() * 50;  // 10-60 м
        double maxAltitude = 10000 + Math.random() * 15000;  // 10,000-25,000 м

        return new Airplane(brand, speed, weight, colorString, wingSpan, maxAltitude);
    }

    private Car generateCar() {
        String brand = "CarBrand" + (int)(Math.random() * 100);
        double speed = 50 + Math.random() * 150;  // 50-200 км/ч
        double weight = 800 + Math.random() * 1500;  // 800-2300 кг
        int r = (int)(Math.random() * 256);
        int g = (int)(Math.random() * 256);
        int b = (int)(Math.random() * 256);
        String colorString = String.format("#%02X%02X%02X", r, g, b);
        return new Car(brand, speed, weight, colorString);
    }

    // Получение списка всех машин
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        for (Transport transport : transports) {
            if (transport instanceof Car) {
                cars.add((Car) transport);
            }
        }
        return cars;
    }

    // Получение списка всех самолётов
    public List<Airplane> getAirplanes() {
        List<Airplane> airplanes = new ArrayList<>();
        for (Transport transport : transports) {
            if (transport instanceof Airplane) {
                airplanes.add((Airplane) transport);
            }
        }
        return airplanes;
    }

    // Получение списка всех транспортных средств
    public List<Transport> getTransports() {
        return transports;
    }
}
