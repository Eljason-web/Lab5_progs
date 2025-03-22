package org.example.commands;

import org.example.collections.City;
import org.example.utils.CollectionManager;

import java.util.Scanner;

public class AddIfMax implements Commands{
    private final CollectionManager collectionManager;

    public AddIfMax(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.addIfMax();
    }

    @Override
    public void execute(String arg) {

    }
}
