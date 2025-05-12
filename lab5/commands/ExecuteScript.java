package org.example.commands;

import org.example.utils.CommandManager;
import org.example.utils.FileManager;

import java.util.ArrayList;

public class ExecuteScript implements Commands {
    private final CommandManager commandManager;

    public ExecuteScript(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(String arg) throws Exception {
        FileManager fileManager = new FileManager();
        ArrayList<String> commands = fileManager.loadCommandsFromScript(arg);

        for (String command : commands) {
            System.out.println("COMMAND FROM SCRIPT: " + command);
            commandManager.executeCommand(command);
        }
    }
}
