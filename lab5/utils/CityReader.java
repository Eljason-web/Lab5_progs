package org.example.utils;

import org.example.collections.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class CityReader {
     private final Scanner scanner;

    public CityReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public City collectCityData(){

        String name = addName();

        Coordinates coordinates = addCoordinate();

        float area = addArea();

        Long population = addPopulation();

        double metersAboveSeaLevel = addMetersAboveSeaLevel();

        float agglomeration = addAgglomeration();

        Climate climate = addClimate();

        Government government = addGovernment();

        Human governor = addGovernor();

        return new City(0, name, coordinates, LocalDate.now(), area, population, metersAboveSeaLevel, agglomeration, climate, government, governor);
    }

    protected String addName(){
        System.out.print("Enter name: ");
        return scanner.next();
    }

    protected Coordinates addCoordinate() {
        Coordinates coordinates = new Coordinates();
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter person coordinates");
                System.out.print("x: ");
                double x = scanner.nextDouble();
                System.out.print("y: ");
                long y = scanner.nextLong();

                coordinates.setX(x);
                coordinates.setY(y);
                validInput = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Invalid formats. x is integer less than 630 and y is double");
                scanner.nextLine();
            }
        }

        return coordinates;
    }

    protected float addArea() {
        float area = 0.0f;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter city area: ");
                area = scanner.nextFloat();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid formats. area is float");
                scanner.nextLine();
            }
        }
        return area;
    }

    protected Long addPopulation() {
        long population = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter city population: ");
                population = scanner.nextLong();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid formats. city is long");
                scanner.nextLine();
            }
        }
        return population;
    }

     protected double addMetersAboveSeaLevel() {
        double meters = 0.0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter meters above sea level: ");
                meters = scanner.nextDouble();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid formats. meters is double");
                scanner.nextLine();
            }
        }
        return meters;
    }


     protected float addAgglomeration() {
        float agglomeration = 0.0f;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter agglomeration:  ");
                agglomeration = scanner.nextFloat();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid format. Agglomeration must be a float.");
                scanner.nextLine();
            }
        }

        return agglomeration;
    }

     protected Climate addClimate() {
        Climate climate = null;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter climate (TROPICAL_SAVANNA, HUMID_CONTINENTAL, STEPPE): ");
                String input = scanner.next().toUpperCase();
                climate = Climate.valueOf(input);
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid climate. Please choose a valid climate (TROPICAL_SAVANNA, HUMID CONTINENTAL, STEPPE).");
                scanner.nextLine();

            }
        }

        return climate;
    }


     protected Government addGovernment() {
        Government government = null;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter government type (DESPOTISM, NOOCRACY, TIMOCRACY etc.): ");
                String input = scanner.next().toUpperCase();
                government = Government.valueOf(input);
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid government type. Please choose a valid government.");
                scanner.nextLine();
            }
        }
        return government;
    }

     protected Human addGovernor() {
        LocalDateTime birthday = null;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter governor's  birthday: ");

                //input for year
                System.out.println("Enter the year: ");
                int year = scanner.nextInt();
                if (year < 1900 || year > 2025) throw new Exception("Allowed year is from 1900");

                //input for month
                System.out.println("Enter the month (1 to 12): ");
                int month = scanner.nextInt();
                //input for day
                System.out.println("Enter the day: ");
                int day = scanner.nextInt();

                birthday = LocalDateTime.of(year, month, day, 0, 0);
                validInput = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }

        return new Human(birthday);

    }
}
