package org.example.commands;


import org.example.collections.City;
import org.example.utils.CityReader;
import org.example.utils.CollectionManager;

import java.util.Scanner;

public class Add implements Commands{
    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        CityReader cityReader = new CityReader(scanner);
        City newCity = cityReader.collectCityData();
        collectionManager.add(newCity);
        System.out.println("City added: " + newCity.getName());
    }

    @Override
    public void execute(String arg) {

    }
}package org.example.commands;

import org.example.utils.CollectionManager;

public class Add implements Commands{
    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    @Override
    public void execute() {
       collectionManager.addCity();
    }

    @Override
    public void execute(String arg) {

    }
}
