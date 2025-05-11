package org.example.utils;

import org.example.collections.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CityReader {
    private final Scanner scanner;

    public CityReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public City collectCityData() {

        String name = addName();

        Coordinates coordinates = addCoordinate();

        float area = addArea();

        Long population = addPopulation();

        double metersAboveSeaLevel = addMetersAboveSeaLevel();

        float agglomeration = addAgglomeration();

        Climate climate = addClimate();

        Government government = addGovernment();

        Human governor = addGovernor();

        return new City(0, name, coordinates, LocalDate.now(), area, population, metersAboveSeaLevel, agglomeration,
                climate, government, governor);
    }

    protected String addName() {
        String name = "";
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter name: ");
                name = scanner.nextLine().trim();
                if(name.isEmpty()) {
                    throw new IllegalArgumentException("Name can not be empty");
                }
                if (!name.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9\\-\\s]+$")) { // must contain at least a letter. Numbers, space and hyphen are also permissible
                    throw new IllegalArgumentException("Name can only contain letters, spaces, and hyphens.");
                }
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return name;
    }

    protected Coordinates addCoordinate() {
        Coordinates coordinates = new Coordinates();
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter person coordinates");
                //Coordinates constraints with respect to earth's geometry x [-90.0; 90.0], y [-180.0, 180.0]

                System.out.print("x (double): ");
                double x = Double.parseDouble(scanner.nextLine().trim());
                if(x < -90.0 || x > 90.0 || Double.isNaN(x)) {
                    throw new IllegalArgumentException("x must be within -90.0 and 90.0");
                }

                System.out.print("y (long, without 'L'): ");
                long y = Long.parseLong(scanner.nextLine().trim());
                if(y < -180 || y > 180) {
                    throw new IllegalArgumentException("y must be within -180 and 180");
                }


                coordinates.setX(x);
                coordinates.setY(y);
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Invalid input - " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input, x must be double and y must be long");
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
                area = Float.parseFloat(scanner.nextLine().trim());

                if(area<=0 || Float.isNaN(area) || Float.isInfinite(area)) {
                    throw new IllegalArgumentException("Area must be finite float greater than 0");
                }

                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid formats. area is float");
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
                population = Long.parseLong(scanner.nextLine().trim());

                if(population<=0) {
                    throw new IllegalArgumentException("Population must be long greater than 0");
                }

                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Population must be a long positive number");
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
                meters = Double.parseDouble(scanner.nextLine().trim());

                if(meters<0) {
                    throw new IllegalArgumentException("Meters must be greater than 0");
                }

                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid formats. meters is double");
            } catch (IllegalArgumentException e) {
                System.out.println("Illegal input " + e.getMessage());
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
                agglomeration = Float.parseFloat(scanner.nextLine().trim());

                if(Float.isNaN(agglomeration) || Float.isInfinite(agglomeration)) {
                    throw new IllegalArgumentException("Agglomeration must be a finite float");
                }
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid format. Agglomeration must be a float.");
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
                String input = scanner.nextLine().toUpperCase().trim();
                climate = Climate.valueOf(input);
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid climate. Please choose a valid climate (TROPICAL_SAVANNA, HUMID CONTINENTAL, STEPPE).");
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
                String input = scanner.next().toUpperCase().trim();
                government = Government.valueOf(input);
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid government type. Please choose a valid government.");
            }
        }
        return government;
    }

    protected Human addGovernor() {
        Human governor = new Human();
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter the birthday of the governor (format: yyyy-MM-dd): ");
                String input = scanner.next();
                scanner.nextLine(); // consume newline

                LocalDate date = LocalDate.parse(input);
                LocalDateTime birthday = date.atStartOfDay();

                governor.setBirthday(birthday);
                validInput = true;

            } catch (Exception e) {
                System.out.println("Incorrect date entry");
                scanner.nextLine();
            }
        }
        return governor;
    }
}
