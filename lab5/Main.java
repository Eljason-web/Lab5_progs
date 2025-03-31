package org.example;

import org.example.collections.CityCollection;
import org.example.utils.CollectionManager;
import org.example.utils.CommandManager;
import org.example.utils.ConsoleManager;
import org.example.utils.FileManager;



public class Main {
        public static void main(String[] args) {
            CityCollection cityCollection = new CityCollection();
            CollectionManager collectionManager = new CollectionManager(cityCollection);
            FileManager fileManager = new FileManager(collectionManager);

            String fileName = System.getenv("FILE_COLLECTION_PATH");


            if (fileName == null || fileName.isEmpty()) {
                    System.out.println("Environment variable FILE_COLLECTION_PATH is not set or is empty.");
                    return;
            }

                fileManager.loadCollectionFromXml(fileName); //load using environmental variable
//              fileManager.loadCollectionFromXml(args[0]); //load  using command line variable
//              fileManager.loadCollectionFromXml("storage.xml"); //to load using name of file directly


            CommandManager commandManager = new CommandManager(collectionManager);
            ConsoleManager consoleManager = new ConsoleManager(commandManager);
            consoleManager.interactiveMode(); //triggers interactive mode with user
        }
}
