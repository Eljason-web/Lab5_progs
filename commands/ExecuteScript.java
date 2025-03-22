package org.example.commands;

import org.example.utils.CollectionManager;
import org.example.utils.CommandManager;
import org.example.utils.FileManager;

import java.util.ArrayList;

public class ExecuteScript implements Commands{
    private final CollectionManager collectionManager;

    public ExecuteScript(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(String arg) {
        FileManager fileManager = new FileManager(collectionManager);
        CommandManager commandManager = new CommandManager(collectionManager);
        ArrayList<String> commands = fileManager.loadCommandsFromScript(arg);

        for(String command : commands){
            System.out.println("COMMAND FROM SCRIPT: " + command);
            commandManager.executeCommand(command);
        }
    }
}
