package org.example.collections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "list")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityCollection {
    @XmlElement(name = "city")
    private final ArrayDeque<City> cities = new ArrayDeque<>();

    public CityCollection(){}

    public ArrayDeque<City> getCities() {
        return cities;
    }

    public void addCity(City city) {
        cities.add(city);
        List<City> cityList = new ArrayList<>(cities);
        cityList.sort(null);
        cities.clear();
        cities.addAll(cityList);
    }

    public void addAllCities(ArrayDeque<City> loadedCollection){
        cities.clear();
        cities.addAll(loadedCollection);
    }

    public void removeCity(City city) {
        cities.remove(city);
    }

    public void clearCities() {
        cities.clear();
    }

    public int size() {
        return cities.size();
    }


}
