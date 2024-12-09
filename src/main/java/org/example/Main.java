package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Шаг 1: Генерация случайного количества транспортных средств
        TransportHandler handler = new TransportHandler();
        int count = 10 + (int)(Math.random() * 11);  // От 10 до 20 объектов
        handler.generateTransports(count);
        System.out.println("Generated " + count + " transports.");

        // Шаг 2: Сохранение в базу данных
        Database.createTable();
        Database.insertTransports(handler.getTransports());

        // Шаг 3: Извлечение данных из БД
        List<Transport> transports = Database.getTransports();

        // Шаг 4: Вывод информации о транспорте
        for (Transport transport : transports) {
            System.out.println(transport);
        }

        // Шаг 5: Создание графика
        Graph graph = new Graph(transports);
        graph.setSize(800, 600);
        graph.setVisible(true);
    }
}
