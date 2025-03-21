package org.example.collections;

//import jakarta.xml.bind.annotation.XmlAccessType;
//import jakarta.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    private Double x; //Поле не может быть null
    private Long y; //Поле не может быть null

    public Coordinates(Double x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        if (x == null) {
            throw new IllegalArgumentException("X coordinates cannot be null.");
        }
        this.x = x;
    }

    public Long getY() {
        if (y == null) {
            throw new IllegalArgumentException("Y coordinate cannot be null.");
        }
        return y;
    }

    public void setY(Long y){
        this.y = y;
    }

    @Override
    public String toString() {
        String string = "Coordinates: \n";
        string += "\t x: " + x + "\n";
        string += "\t y: " + y;

        return string;
    }


}

