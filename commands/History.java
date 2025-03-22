package org.example.commands;

import java.util.ArrayDeque;

public class History implements Commands{
    private final ArrayDeque<String> history;

    public History(ArrayDeque<String> history) {
        this.history = history;
    }

    @Override
    public void execute() {
        System.out.println("Last " + history.size() + " commands:");
        for (String command : history) {
            System.out.println(command);
        }
    }

    @Override
    public void execute(String arg) {

    }
}
