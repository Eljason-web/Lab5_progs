package org.example.commands;

import org.example.collections.City;
import org.example.utils.CollectionManager;

public class Show implements Commands {
    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {

    }

    @Override
    public void execute() {
        if (collectionManager.getCityCollection().getCities().isEmpty()) {
            System.out.println("We do not have any cities in our collection");
        } else {
            StringBuilder result = new StringBuilder("LIST OF CITIES IN THE COLLECTION:\n");

            for (City city : collectionManager.getCityCollection().getCities()) {
                result.append(city.toString()).append("\n\n");
            }

            System.out.println(result);
        }
    }
}
