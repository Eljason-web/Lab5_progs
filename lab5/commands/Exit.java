package org.example.commands;

import org.example.utils.CollectionManager;

public class Exit implements Commands{
    public void execute() {
        System.out.println("PROGRAM CLOSING...");
        System.exit(-1);
    }

    @Override
    public void execute(String arg) {

    }
}
