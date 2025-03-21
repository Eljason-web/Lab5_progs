package org.example.commands;

//import org.example.utils.CollectionManager;

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