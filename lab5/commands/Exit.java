package org.example.commands;

public class Exit implements Commands {
    public void execute() {
        System.out.println("PROGRAM CLOSING...");
    }

    @Override
    public void execute(String arg) {

    }
}
