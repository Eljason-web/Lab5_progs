package org.example;

import org.example.collections.*;
import org.example.utils.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;


public class Main {
        public static void main(String[] args) {
                CollectionManager collectionManager = new CollectionManager();
                FileManager fileManager = new FileManager(collectionManager);

                String fileName = System.getenv("COLLECTION_FILE_PATH");

                if (fileName == null || fileName.isEmpty()) {
                        System.out.println("Environment variable COLLECTION_FILE_PATH is not set or is empty.");
                        return;
                }

                fileManager.loadCollectionFromXml(fileName); //load using environmental variable
//               fileManager.loadCollectionFromXml(args[0]); //load  using command line variable
//                fileManager.loadCollectionFromXml("storage.xml"); //to load using name of file directly


                CommandManager commandManager = new CommandManager(collectionManager);
                ConsoleManager consoleManager = new ConsoleManager(commandManager, new Scanner(System.in));
                consoleManager.interactiveMode(); //triggers interactive mode with user
        }
}