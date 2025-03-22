package org.example.commands;

import org.example.utils.CollectionManager;

public class CountByAgglomeration implements Commands{
    private final CollectionManager collectionManager;

    public CountByAgglomeration(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(String arg) {
        try{
            float agglomerationValue = Float.parseFloat(arg);
            int count = collectionManager.countByAgglomeration(agglomerationValue);
            System.out.println("Number of cities with agglomeration " + arg + " is " + count);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for agglomeration: " + arg);
        }
    }
}
