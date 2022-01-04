package ua.yarynych.demoapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bar {

    @Id
    //дозволяє генерувати нове неповтерне значення
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String preview;
    private String description;
    private int price;
    private int popularity;

    public Bar() {
    }

    public Bar(String name, String preview, String fullText, int price, int popularity) {
        this.name = name;
        this.preview = preview;
        this.description = fullText;
        this.price = price;
        this.popularity = popularity;
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

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
