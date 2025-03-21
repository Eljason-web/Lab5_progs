package org.example.commands;

import org.example.collections.City;
import org.example.utils.CollectionManager;

import java.util.Scanner;

public class AddIfMin implements Commands{
    private final CollectionManager collectionManager;

    public AddIfMin(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        City newCity = collectionManager.readCityFromScanner(scanner);

        if(collectionManager.getCities().isEmpty()) {
            collectionManager.add(newCity);
        } else {
            int currentMinId = collectionManager.getCities().getFirst().getId();
            if(newCity.getId() < currentMinId) {
                collectionManager.add(newCity);
            } else {
                System.out.println("New city is not minimum and cannot be added");
            }
        }
    }

    @Override
    public void execute(String arg) {

    }
}
