package org.example.utils;

import org.example.collections.*;

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
    private final Scanner scanner;

    public CollectionManager(Scanner scanner) {
        this.scanner = scanner;
    }

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

    public void addToCollection(City city) {
        if (city != null) {
            cities.add(city);
            //sorting logic implementing the comparable in city
            List<City> cityList = new ArrayList<>(cities);
            cityList.sort(null);
            cities.clear();
            cities.addAll(cityList);

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

    public void add() {
        //for automatically generated values
        int id = generateId();
        System.out.println("ID automatically generated: " + id);

        LocalDate creationDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = creationDate.format(formatter);
        System.out.println("Creation date was automatically generated: " + formattedDate);

        CityReader cityReader = new CityReader(scanner);
        City newCity = cityReader.collectCityData();


        newCity.setId(id);
        newCity.setCreationDate(creationDate);

        addToCollection(newCity);

    }

    public void addIfMax(){
        //for automatically generated values
        int id = generateId();
        System.out.println("ID automatically generated: " + id);

        LocalDate creationDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = creationDate.format(formatter);
        System.out.println("Creation date was automatically generated: " + formattedDate);


        Scanner scanner = new Scanner(System.in);
        CityReader cityReader = new CityReader(scanner);
        City newCity = cityReader.collectCityData();

        // I use here population to determine max city
        boolean isMaxCity = true;
        for (City city : cities){
            if(city.getPopulation() > newCity.getPopulation()) {
                isMaxCity = false;
                break;
            }
        }

        if(isMaxCity) {
            addToCollection(newCity);
        } else {
            System.out.println("New city added is not max city");
        }
    }

    public void addIfMin(){
        //for automatically generated values
        int id = generateId();
        System.out.println("ID automatically generated: " + id);

        LocalDate creationDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = creationDate.format(formatter);
        System.out.println("Creation date was automatically generated: " + formattedDate);


        Scanner scanner = new Scanner(System.in);
        CityReader cityReader = new CityReader(scanner);
        City newCity = cityReader.collectCityData();

        // I use here population to determine max city
        boolean isMinCity = true;
        for (City city : cities){
            if(city.getPopulation() < newCity.getPopulation()) {
                isMinCity = false;
                break;
            }
        }

        if(isMinCity) {
            addToCollection(newCity);
        } else {
            System.out.println("New city added is not minimum city");
        }
    }

    private int generateId() {
        if(cities.isEmpty()) {
            return 1;
        }
        return cities.getLast().getId() + 1; // works because array deque is already sorted
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
        CityReader cityReader = new CityReader(scanner);

        String name = cityReader.addName();

        Coordinates coordinates = cityReader.addCoordinate();
        float area = cityReader.addArea();
        Long population = cityReader.addPopulation();
        double metersAboveSeaLevel = cityReader.addMetersAboveSeaLevel();
        float agglomeration = cityReader.addAgglomeration();
        Climate climate = cityReader.addClimate();
        Government government = cityReader.addGovernment();
        Human governor = cityReader.addGovernor();

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

    public int countByAgglomeration(float agglomerationValue) {
        int count = 0;

        // Loop through cities and compare the agglomeration values
        for (City city : cities) {
            if (city.getAgglomeration() == agglomerationValue) {
                count++;
            }
        }

        return count;
    }

}



