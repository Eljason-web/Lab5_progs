package org.example.utils;

import java.util.Scanner;

public class ConsoleManager {
    private final CommandManager commandManager;
    private final Scanner scanner;

    public ConsoleManager(CommandManager commandManager, Scanner scanner){
        this.commandManager = commandManager;
        this.scanner = new Scanner(System.in);
    }

    public void interactiveMode() {
        System.out.println("Welcome to the City Manager!");
        while(true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine().trim();
            commandManager.executeCommand(command);
        }
    }
}
