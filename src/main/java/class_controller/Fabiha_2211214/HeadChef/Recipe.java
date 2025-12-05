package class_controller.Fabiha_2211214.HeadChef;

import java.io.Serializable;

public class Recipe implements Serializable {
    private int id;
    private String name;
    private double price;
    private String status;
    private String regions;

    public Recipe(int id, String name, double price, String status, String regions) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.regions = regions;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }
    public String getRegions() { return regions; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }
    public void setRegions(String regions) { this.regions = regions; }
}