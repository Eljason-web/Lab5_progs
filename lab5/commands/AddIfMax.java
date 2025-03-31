package org.example.commands;

import org.example.collections.City;
import org.example.utils.CityReader;
import org.example.utils.CollectionManager;

import java.util.Scanner;


public class AddIfMax implements Commands{
    private final CollectionManager collectionManager;

    public AddIfMax(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        CityReader cityReader = new CityReader(scanner);
        City newCity = cityReader.collectCityData();

        // I use here population to determine max city
        boolean isMaxCity = true;
        for (City city : collectionManager.getCityCollection().getCities()){
            if(city.getPopulation() > newCity.getPopulation()) {
                isMaxCity = false;
                break;
            }
        }

        if(isMaxCity) {
            collectionManager.add(newCity);
            System.out.println("City added: " + newCity.getName());
        } else {
            System.out.println("New city added is not max city");
        }
    }

    @Override
    public void execute(String arg) {

    }
}
