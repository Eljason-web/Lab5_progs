package org.example.commands;

import org.example.collections.City;
import org.example.utils.CollectionManager;

public class PrintFieldAscendingMetersAboveSeaLevel implements Commands {
    public final CollectionManager collectionManager;

    public PrintFieldAscendingMetersAboveSeaLevel(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        if (collectionManager.getCityCollection().getCities().isEmpty()) {
            System.out.println("The collection is empty.");
            return;
        }

        collectionManager.getCityCollection().getCities().stream()
                .map(City::getMetersAboveSeaLevel)
                .sorted()
                .forEach(value -> System.out.println(value + "meters"));

    }

    @Override
    public void execute(String arg) {
        System.out.println("This command does not arguments.");

    }
}
