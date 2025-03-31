package org.example.commands;

import org.example.utils.CollectionManager;

public class Info implements Commands{
    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {

    }

    @Override
    public void execute() {
        String infoString = collectionManager.info();
        System.out.println(infoString);
    }
}
