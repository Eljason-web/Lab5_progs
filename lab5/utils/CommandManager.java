package org.example.utils;
import org.example.commands.*;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final CollectionManager collectionManager;
    private final ArrayDeque<String> commandHistory = new ArrayDeque<>();
    private final Map<String, Commands> commandsMap = new HashMap<>();

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;

        commandsMap.put("show", new Show(collectionManager));
        commandsMap.put("help", new Help());
        commandsMap.put("info", new Info(collectionManager));
        commandsMap.put("exit", new Exit());
        commandsMap.put("save", new Save(collectionManager));
        commandsMap.put("add", new Add(collectionManager));
        commandsMap.put("clear", new Clear(collectionManager));
        commandsMap.put("history", new History(commandHistory));
        commandsMap.put("add_if_min", new AddIfMin(collectionManager));
        commandsMap.put("add_if_max", new AddIfMax(collectionManager));
        commandsMap.put("update", new Update(collectionManager));
        commandsMap.put("remove_by_id", new RemoveById(collectionManager));
        commandsMap.put("execute_script", new ExecuteScript(collectionManager, this));
        commandsMap.put("count_by_agglomeration", new CountByAgglomeration(collectionManager));
        commandsMap.put("print_field_ascending_meters_above_sea_level", new PrintFieldAscendingMetersAboveSeaLevel(collectionManager));
        commandsMap.put("filter_by_government", new FilterByGovernment(collectionManager));
    }


    public void executeCommand(String command) {
        String[] commandArray = command.split(" ");
        String c = commandArray[0];
        String arg = "";

        if(commandArray.length == 2){
            arg = commandArray[1];
        }

        Commands commandInstance = commandsMap.get(c);

        if (commandInstance != null) {
            if (arg.isEmpty()) {
                commandInstance.execute();
            } else {
                commandInstance.execute(arg);
            }
            addToHistory(c);
        } else {
            System.out.println("Unknown command. Enter 'help' for a list of commands.");
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
