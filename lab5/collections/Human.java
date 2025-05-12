package org.example.collections;
import org.example.utils.LocalDateTimeAdapter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Human {
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthday;

    public Human(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Human() {
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString(){
        //we might need a formatter later if the to string is not understandable
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "birthday: " +birthday.format(formatter);
    }
}
