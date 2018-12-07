package at.htl.nvs.entity;

public class Product {
    public Product(Long id, String name, String weight, Double price) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public Product() {

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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    Long id;
    String name;
    String weight;
    Double price;


}
