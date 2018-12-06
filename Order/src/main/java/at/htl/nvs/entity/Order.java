package at.htl.nvs.entity;


public class Order {
    Long ID;
    Customer customer;
    Product product;
    Double price;

    public Order(Long ID, Customer customer, Product product, Double price) {
        this.ID = ID;
        this.customer = customer;
        this.product = product;
        this.price = price;
    }

    public Order() {

    }

    public Long getID() {

        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
