package org.example.utils;

import org.example.collections.*;

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



public class CollectionManager {
    private final CityCollection cityCollection;

    public CollectionManager(CityCollection cityCollection) {
        this.cityCollection = cityCollection;
    }

    public CityCollection getCityCollection() {
        return cityCollection;
    }

    public void setCityCollection(ArrayDeque<City> loadedCollection) {
        if(loadedCollection != null) {
           cityCollection.addAllCities(loadedCollection);
        } else {
            System.out.println("Warning: Attempted to load null collection");
        }
    }

    public String show() {
        if (cityCollection.getCities().isEmpty()) {
            return "We do not have any cities in our collection";
        } else {
            StringBuilder result = new StringBuilder("LIST OF CITIES IN THE COLLECTION:\n");
            for (City city : cityCollection.getCities()) {
                result.append(city.toString()).append("\n\n");
            }
            return result.toString();
        }
    }

    public String info() {
        String initializationDate = getInitializationDate();
        int numberOfElements = cityCollection.getCities().size();
        return "Information about collection\n" +
                "Type: " + cityCollection.getCities().getClass() + "\n" +
                "Initialization date: " + initializationDate + "\n" +
                "Number of cities: " + numberOfElements;
    }

    private String getInitializationDate() {
        String date = "";
        try {
            String fileName = System.getenv("FILE_COLLECTION_PATH");
            Path path = Paths.get(fileName);
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

    public void add(City city) {
        //for automatically generated values
        int id = generateId();
        LocalDate creationDate = LocalDate.now();

        city.setId(id);
        city.setCreationDate(creationDate);

        cityCollection.addCity(city);
    }

    private int generateId() {
        if(cityCollection.getCities().isEmpty()) {
            return 1;
        }
        return cityCollection.getCities().getLast().getId() + 1; // works because array deque is already sorted
    }

    public City findCityById(int id){
        for(City city : cityCollection.getCities()){
            if(city.getId() == id) {
                return city;
            }
        }
        return null;
    }

    public void clearCollection() {
            cityCollection.clearCities();
    }

    public void updateCity(City originalCity, City cityUpdates) {
        originalCity.setName(cityUpdates.getName());
        originalCity.setCoordinates(cityUpdates.getCoordinates());
        originalCity.setArea(cityUpdates.getArea());
        originalCity.setPopulation(cityUpdates.getPopulation());
        originalCity.setMetersAboveSeaLevel(cityUpdates.getMetersAboveSeaLevel());
        originalCity.setAgglomeration(cityUpdates.getAgglomeration());
        originalCity.setClimate(cityUpdates.getClimate());
        originalCity.setGovernment(cityUpdates.getGovernment());
        originalCity.setGovernor(cityUpdates.getGovernor());


    }

    public void removeCity(City city) {
        cityCollection.removeCity(city);
    }

    public int countByAgglomeration(float agglomerationValue) {
        int count = 0;

        // Loop through cities and compare the agglomeration values
        for (City city : cityCollection.getCities()) {
            if (city.getAgglomeration() == agglomerationValue) {
                count++;
            }
        }

        return count;
    }

}



