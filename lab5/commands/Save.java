package org.example.commands;

import org.example.utils.CollectionManager;
import org.example.utils.FileManager;

public class Save implements Commands{
    private final CollectionManager collectionManager;
    public Save(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {

    }

    @Override
    public void execute() {
        FileManager fileManager = new FileManager(collectionManager);
        fileManager.saveToXml(System.getenv("FILE_COLLECTION_PATH"));
    }
}
