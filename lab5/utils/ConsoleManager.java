package org.example.utils;

import java.util.Scanner;

public class ConsoleManager {
    private final CommandManager commandManager;
    private final Scanner scanner;

    public ConsoleManager(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.scanner = new Scanner(System.in);
    }

    public void interactiveMode() throws Exception {
        System.out.println("Welcome to the City Manager!");
        try {
            while (true) {
                System.out.print("Enter a command: ");
                String command = scanner.nextLine().trim();
                if (command.isEmpty()) {
                    System.out.println("Error: Command cannot be empty. Please try again.");
                    continue;
                }

                if ("exit".equalsIgnoreCase(command)) {
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                }

                boolean success = commandManager.executeCommand(command);
                if (!success) {
                    System.out.println("Error: Unrecognized command. Type 'help' for a list of available commands.");
                }
            }
        } finally {
            scanner.close();
        }
    }
}
