package org.example.commands;

import org.example.collections.*;
import org.example.utils.CityReader;
import org.example.utils.CollectionManager;

import java.util.Scanner;

public class Update implements Commands{
    private final CollectionManager collectionManager;

    public Update(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(String arg) {
        try{
            int id = Integer.parseInt(arg);
            City cityWithId = collectionManager.findCityById(id);
            if(cityWithId == null){
                System.out.println("City with ID " + arg + " does not exist");
            } else {
                Scanner scanner = new Scanner(System.in);
                CityReader cityReader = new CityReader(scanner);
                City cityUpdates = cityReader.collectCityData();
                collectionManager.updateCity(cityWithId, cityUpdates);
                System.out.println("City with id: " + cityWithId.getId() + " has been updated");
            }
        } catch (Exception e){
            System.out.println("Invalid Id format");
        }
    }
}
