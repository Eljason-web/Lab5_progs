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
        collectionManager.getCities().stream()
                .map(City::getMetersAboveSeaLevel)
                .forEach(System.out::println);

    }

    @Override
    public void execute(String arg) {

    }
}
