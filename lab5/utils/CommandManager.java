package org.example.utils;

import org.example.commands.*;

import java.util.ArrayDeque;

public class CommandManager {
    private final CollectionManager collectionManager;
    private final ArrayDeque<String> commandHistory = new ArrayDeque<>();

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void executeCommand(String command) {
        String[] commandArray = command.split(" ");
        String c = commandArray[0];
        String arg = "";

        if(commandArray.length == 2){
            arg = commandArray[1];
        }

        switch (c) {
            case "show":
                new Show(collectionManager).execute();
                addToHistory(c);
                break;
            case "help":
                new Help().execute();
                addToHistory(c);
                break;
            case "info":
                new Info(collectionManager).execute();
                addToHistory(c);
                break;
            case "exit":
                new Exit().execute();
                addToHistory(c);
                break;
            case "save":
                new Save(collectionManager).execute();
                addToHistory(c);
                break;
            case "add":
                new Add(collectionManager).execute();
                addToHistory(c);
                break;
            case "clear":
                new Clear(collectionManager).execute();
                addToHistory(c);
                break;
            case "history":
                new History(commandHistory).execute();
                addToHistory(c);
                break;
            case "add_if_min":
                new AddIfMin(collectionManager).execute();
                addToHistory(c);
                break;
            case "add_if_max":
                new AddIfMax(collectionManager).execute();
                addToHistory(c);
            case "update":
                new Update(collectionManager).execute(arg);
                addToHistory(c);
                break;
            case "remove_by_id":
                new RemoveById(collectionManager).execute(arg);
                addToHistory(c);
                break;
            case "execute_script":
                new ExecuteScript(collectionManager).execute(arg);
                addToHistory(c);
                break;
            case "count_by_agglomeration":
                new CountByAgglomeration(collectionManager).execute(arg);
                addToHistory(c);
                break;
            case "print_field_ascending_meters_above_sea_level":
                new PrintFieldAscendingMetersAboveSeaLevel(collectionManager).execute();
                addToHistory(c);
                break;
            case "filter_by_government":
                new FilterByGovernment(collectionManager).execute(arg);
                addToHistory(c);
                break;
            default:
                System.out.println("Unknown command. Enter help for a list of commands");
        }

        System.out.println();
    }

    private void addToHistory(String commandName) {
        // Keep only the last 11 commands.
        if (commandHistory.size() == 11) {
            commandHistory.removeFirst();
        }
        commandHistory.add(commandName);
    }




}
