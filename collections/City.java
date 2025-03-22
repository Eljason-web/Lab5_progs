package org.example.collections;

import org.example.utils.LocalDateAdapter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
public class City implements Comparable<City> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float area; //Значение поля должно быть больше 0, Поле не может быть null
    private Long population; //Значение поля должно быть больше 0, Поле не может быть null
    private double metersAboveSeaLevel;
    private float agglomeration;
    private Climate climate; //Поле не может быть null
    private Government government; //Поле может быть null
    private Human governor; //Поле может быть null

    public City(int id, String name, Coordinates coordinates, LocalDate creationDate, Float area, Long population, double metersAboveSeaLevel, float agglomeration, Climate climate, Government government, Human governor) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.agglomeration = agglomeration;
        this.climate = climate;
        this.government = government;
        this.governor = governor;
    }

    public City() {
    }

    @Override
    public int compareTo(City otherCity) {
        return Integer.compare(this.id, otherCity.id);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be null or empty.");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {

        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates cannot be null.");
        }
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        if (area == null || area <= 0) {
            throw new IllegalArgumentException("Area must be greater than 0 and cannot be null.");
        }
        this.area = area;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        if (population == null || population <= 0) {
            throw new IllegalArgumentException("Population must be greater than 0 and cannot be null.");
        }
        this.population = population;
    }

    public double getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(double metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public float getAgglomeration() {
        return agglomeration;
    }

    public void setAgglomeration(float agglomeration) {
        this.agglomeration = agglomeration;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        if (climate == null) {
            throw new IllegalArgumentException("Climate cannot be null.");
        }
        this.climate = climate;
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CITY DATA: \n")
            .append("id: ").append(id).append("\n")
            .append("name: ").append(name).append("\n")
            .append(coordinates.toString()).append("\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        sb.append("creation date: ").append(creationDate.format(formatter)).append("\n")
                .append("area: ").append(area).append("\n")
                .append("population: ").append(population).append("\n")
                .append("meters above sea level: ").append(metersAboveSeaLevel).append("\n")
                .append("agglomeration: ").append(agglomeration).append("\n")
                .append("Climate: ").append(climate).append("\n")
                .append("Government: ").append(government).append("\n")
                .append("Human: ").append(governor);


        return sb.toString();
    }
}
