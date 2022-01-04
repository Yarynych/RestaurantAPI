package ua.yarynych.demoapi.model;

import javax.persistence.*;

@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String first_name;
    private String position;

    public Worker() {
    }

    public Worker(String name, String first_name, String position) {
        this.name = name;
        this.first_name = first_name;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirs_name() {
        return first_name;
    }

    public void setFirs_name(String firs_name) {
        this.first_name = firs_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String description) {
        this.position = description;
    }
}
