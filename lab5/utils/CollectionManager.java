package org.example.utils;

//import jakarta.xml.bind.annotation.XmlAccessType;
//import jakarta.xml.bind.annotation.XmlAccessorType;
//import jakarta.xml.bind.annotation.XmlElement;
//import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.collections.*;

//import javax.xml.namespace.QName;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


@XmlRootElement(name = "list")
@XmlAccessorType(XmlAccessType.FIELD)
public class CollectionManager {
    @XmlElement(name = "city")
    private final ArrayDeque<City> cities = new ArrayDeque<>();

    public ArrayDeque<City> getCities() {
        return cities;
    }

    public void setCities(ArrayDeque<City> loadedCollection) {
        if(loadedCollection != null) {
            cities.clear();
            cities.addAll(loadedCollection);
        } else {
            System.out.println("Warning: Attempted to load null collection");
        }
    }

    private void sortCollection(){
        List<City> cityList = new ArrayList<>(cities);
        cityList.sort(Comparator.comparingInt(City::getId));
        clear();
        cities.addAll(cityList);
    }

    public void add(City city) {
        if (city != null) {
            cities.add(city);
            sortCollection();
            System.out.println("City added: " + city.getName());
        } else {
            System.out.println("City doesn't have some required details");
        }
    }

    public void show() {
        if (cities.isEmpty()) {
            System.out.println("We do not have any cities in our collection");
        } else {
            System.out.println("LIST OF CITIES ON THE COLLECTION");
            for (City city : cities) {
                String cityString = city.toString();
                System.out.println(cityString);
                System.out.println();
            }
        }
    }

    public void info() {
        System.out.println("Information about collection");
        System.out.println("Type: " + cities.getClass());
        String initializationDate = getInitializationDate();
        System.out.println("Initialization date: " + initializationDate);
        int numberOfElements = cities.size();
        System.out.println("Number of cities: " + numberOfElements);
    }

    private String getInitializationDate() {
        String date = "";
        try {
            Path path = Paths.get("storage.xml");
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            FileTime fileTime = attrs.creationTime();
            Instant instant = fileTime.toInstant();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            date = localDateTime.format(formatter);

        } catch (IOException e) {
            System.out.println("exception: " + e.getMessage());
        }
        return date;
    }

    public void addCity() {
        Scanner scanner = new Scanner(System.in);
        City newCity = readCityFromScanner(scanner);
        add(newCity);
    }

    public City readCityFromScanner(Scanner scanner) {
        int id = generateId();
        System.out.println("ID automatically generated: " + id);

        System.out.print("Enter name: ");
        String name = scanner.next();

        Coordinates coordinates = addCoordinate(scanner);

        LocalDate creationDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = creationDate.format(formatter);
        System.out.println("Creation date was automatically generated: " + formattedDate);

        float area = addArea(scanner);
        Long population = addPopulation(scanner);
        double metersAboveSeaLevel = addMetersAboveSeaLevel(scanner);
        float agglomeration = addAgglomeration(scanner);
        Climate climate = addClimate(scanner);
        Government government = addGovernment(scanner);
        Human governor = addGovernor(scanner);

        return new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, agglomeration, climate, government, governor);

    }


    private int generateId() {
        Random random = new Random();
        int id = random.nextInt(Integer.MAX_VALUE) + 1;
        for (City city : cities) {
            while (city.getId() == id) {
                id = random.nextInt(Integer.MAX_VALUE) + 1;
            }
        }
        return id;
    }

    private Coordinates addCoordinate(Scanner scanner) {
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

    private float addArea(Scanner scanner) {
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

    private Long addPopulation(Scanner scanner) {
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

    private double addMetersAboveSeaLevel(Scanner scanner) {
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


    private float addAgglomeration(Scanner scanner) {
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

    private Climate addClimate(Scanner scanner) {
        Climate climate = null;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter climate (TROPICAL_SAVANNA, HUMIDCONTINENTAL, STEPPE): ");
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


    private Government addGovernment(Scanner scanner) {
        Government government = null;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Enter government type (DEMOCRACY, MONARCHY, etc.): ");
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

    private Human addGovernor(Scanner scanner) {
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
                if (month < 1 || month > 12) {
                    throw new Exception("write month only within 1 to 12");
                }

                //input for day
                System.out.println("Enter the day: ");
                int day = scanner.nextInt();
                if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if (day > 31) throw new Exception("Out of range date in the month");
                } else if(month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day > 30) throw new Exception("Out of range date in the month");
                } else if(month == 2){
                    if (year % 4 == 0) {
                        if (day > 29) throw new Exception("Out of range date in the month");
                    } else {
                        if (day > 28) throw new Exception("Out of range date in the month");
                    }
                }

                birthday = LocalDateTime.of(year, month, day, 0, 0);
                validInput = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }

        return new Human(birthday);

    }

    public void clear() {
        if(!cities.isEmpty()){
            ArrayDeque<City> toRemove = new ArrayDeque<>(cities);
            cities.removeAll(toRemove);
            System.out.println("Collection has been cleared");
        } else {
            System.out.println("Collection is empty");
        }

    }

    public void updateCity(City city) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.next();

        Coordinates coordinates = addCoordinate(scanner);

        float area = addArea(scanner);
        Long population = addPopulation(scanner);
        double metersAboveSeaLevel = addMetersAboveSeaLevel(scanner);
        float agglomeration = addAgglomeration(scanner);
        Climate climate = addClimate(scanner);
        Government government = addGovernment(scanner);
        Human governor = addGovernor(scanner);

        city.setName(name);
        city.setCoordinates(coordinates);
        city.setArea(area);
        city.setPopulation(population);
        city.setMetersAboveSeaLevel(metersAboveSeaLevel);
        city.setAgglomeration(agglomeration);
        city.setClimate(climate);
        city.setGovernment(government);
        city.setGovernor(governor);

        System.out.println("City with id: " + city.getId() + " has been updated");
    }

    public void removeCity(City city) {
        cities.remove(city);
        System.out.println("City with ID " + city.getId() + " has been removed");
    }

    public int countByAgglomeration(String arg) {
        int count = 0;

        // Convert the string argument to a float
        try {
            float agglomerationValue = Float.parseFloat(arg);

            // Loop through cities and compare the agglomeration values
            for (City city : cities) {
                if (city.getAgglomeration() == agglomerationValue) {
                    count++;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for agglomeration: " + arg);
        }

        return count;
    }

}



