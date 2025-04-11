package org.example.commands;

import org.example.utils.CollectionManager;
import org.example.utils.FileManager;

public class Save implements Commands {
    private final CollectionManager collectionManager;
    private final String collectionFilePath;

    public Save(CollectionManager collectionManager, String collectionFilePath) {
        this.collectionManager = collectionManager;
        this.collectionFilePath = collectionFilePath;
    }

    @Override
    public void execute(String arg) {

    }

    @Override
    public void execute() {
        FileManager fileManager = new FileManager();
        fileManager.saveToXml(collectionFilePath, collectionManager.getCityCollection());
    }
}
