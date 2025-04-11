package org.example.commands;

import org.example.utils.CollectionManager;

import java.util.Map;

public class Info implements Commands {
    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String arg) {

    }

    @Override
    public void execute() throws Exception {
        Map<String, String> infoMap = collectionManager.info();
        System.out.println("Collection Info:");
        infoMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
