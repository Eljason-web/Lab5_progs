package org.example.commands;

import org.example.collections.City;
import org.example.utils.CollectionManager;

public class RemoveById implements Commands{
    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(String arg) {
        try{
            int id = Integer.parseInt(arg);
            City city = collectionManager.findCityById(id);

            if(city == null) {
                System.out.println("City with ID " + arg + " does not exist");
            } else {
                collectionManager.removeCity(city);
                System.out.println("City with ID " + city.getId() + " has been removed");
            }
        } catch (Exception e){
            System.out.println("Invalid Id format");
        }
    }
}
