package org.example.utils;

import org.example.collections.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionManager {
    private final CityCollection cityCollection;
    private final String collectionFilePath;

    public CollectionManager(CityCollection cityCollection, String collectionFilePath) {
        this.cityCollection = cityCollection;
        this.collectionFilePath = collectionFilePath;
    }

    public CityCollection getCityCollection() {
        return cityCollection;
    }

    public Map<String, String> info() throws Exception {
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("initialization date", getInitializationDate());
        infoMap.put("number of elements", String.valueOf(cityCollection.getCities().size()));
        infoMap.put("collection type", String.valueOf(cityCollection.getCities().getClass()));

        return infoMap;

    }

    private String getInitializationDate() throws Exception {
        try {
            Path path = Paths.get(collectionFilePath);
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            FileTime fileTime = attrs.creationTime();
            Instant instant = fileTime.toInstant();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            return localDateTime.format(formatter);
        } catch (IOException e) {
            throw new Exception("exception: " + e.getMessage());
        }
    }

    public void add(City city) {
        int id = generateId();
        city.setId(id);
        cityCollection.addCity(city);
    }

    private int generateId() {
        if (cityCollection.getCities().isEmpty()) {
            return 1;
        }
        return cityCollection.getCities().getLast().getId() + 1; // works because array deque is already sorted
    }

    public City findCityById(int id) {
        for (City city : cityCollection.getCities()) {
            if (city.getId() == id) {
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
