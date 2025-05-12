package org.example.commands;

import org.example.collections.City;
import org.example.collections.Government;
import org.example.utils.CollectionManager;

import java.util.Scanner;

public class FilterByGovernment implements Commands {

    private final CollectionManager collectionManager;

    public FilterByGovernment(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    
    @Override
    public void execute() {
        System.out.println("Please provide the government type to filter by (e.g., DEPOTISM, NOOCRACY, TIMOCRACY): ");
        Scanner scanner = new Scanner(System.in);
        String governmentInput = scanner.nextLine().toUpperCase().trim();

        try{
            Government government = Government.valueOf(governmentInput);
            collectionManager.getCities().stream()
                    .filter(city -> city.getGovernment() != null && city.getGovernment() == government)
                    .forEach(city -> System.out.println());
        }   catch (IllegalArgumentException e) {
            System.out.println("Invalid government type provided.");
        }

    }

    @Override
    public void execute(String arg) {
        try {
            // Convert the argument string to a Government enum
            Government government = Government.valueOf(arg.toUpperCase().trim());

            // Filter cities based on the provided government type
            for(City city : collectionManager.getCities()){
                if(city.getGovernment().equals(government)){
                    String cityString = city.toString();
                    System.out.println(cityString);
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid government type provided: " + arg);
        }
    }
}



