package org.example.commands;

import org.example.collections.City;
import org.example.utils.CollectionManager;

import java.util.Scanner;

public class AddIfMin implements Commands{
    private final CollectionManager collectionManager;

    public AddIfMin(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.addIfMin();
    }

    @Override
    public void execute(String arg) {

    }
}
