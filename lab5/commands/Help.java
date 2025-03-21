package org.example.commands;

import org.example.utils.CollectionManager;

public class Help implements Commands{

    public void execute() {
        System.out.println("PROGRAM MANUAL");
        String manual = "help -  display help on available commands \n  " +
                "info - print collection information (type, initialization date, number of elements, etc.) to standard output. \n " +
                "show - print all elements of the collection to standard output in string representation \n " +
                "add {city} - add a new item to the collection \n " +
                "update id {city} - update the value of the collection element whose id is equal to the given one \n " +
                "remove_by_id {id} - remove an element from a collection by its id \n " +
                "clear - clear collection \n " +
                "save - save collection to file \n " +
                "execute_script file_name - read and execute the script from the specified file. The script contains commands in the same form in which the user enters them in interactive mode. \n " +
                "exit: exit the program (without saving to file) \n " +
                "add_if_max {element}: add a new element to a collection if its value is greater than the value of the largest element in that collection \n " +
                "add_if_min {element}: add a new element to a collection if its value is less than the smallest element of that collection \n " +
                "history: print the last 11 commands (without their arguments) \n " +
                "count_by_agglomeration agglomeration: output the number of elements whose agglomeration field value is equal to the specified one \n " +
                "filter_by_government government: output elements whose government field value is equal to the given one \n " +
                "print_field_ascending_meters_above_sea_level: output the values of the metersAboveSeaLevel field of all elements in ascending order";

        System.out.println(manual);
            // Add more help messages for other commands

    }

    @Override
    public void execute(String arg) {

    }
}
