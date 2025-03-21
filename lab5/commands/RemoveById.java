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
            boolean idFound = false;

            for(City city : collectionManager.getCities()) {
                if(city.getId() == id) {
                    idFound = true;
                    collectionManager.removeCity(city);
                    break;
                }
            }

            if(!idFound) {
                System.out.println("City with ID " + arg + " does not exist");
            }
        } catch (Exception e){
            System.out.println("Invalid Id format");
        }
    }
}
