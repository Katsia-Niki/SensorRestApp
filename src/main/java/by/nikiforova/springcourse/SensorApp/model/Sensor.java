package by.nikiforova.springcourse.SensorApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


@Entity
@Table
public class Sensor implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "Sensor name should not be empty")
    @Size(min = 3, max = 50, message = "Sensor name should be between 3 and 50 characters.")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
