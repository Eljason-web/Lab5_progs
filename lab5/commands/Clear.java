package org.example.commands;

import org.example.utils.CollectionManager;

public class Clear implements Commands{
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    @Override
    public void execute() {
        boolean isCollectionEmpty = collectionManager.getCityCollection().getCities().isEmpty();
        if(isCollectionEmpty){
            System.out.println("Collection is empty");
            System.out.println("Collection has been cleared");
        } else {
            collectionManager.clearCollection();
        }
    }

    @Override
    public void execute(String arg) {

    }
}
