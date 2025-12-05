package class_controller.Fabiha_2211214.HeadChef;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private int id;
    private String name;
    private double currentQty;
    private double requiredQty;
    private String stockStatus;
    private String usedInRecipes;

    public Ingredient(int id, String name, double currentQty, double requiredQty, String usedInRecipes) {
        this.id = id;
        this.name = name;
        this.currentQty = currentQty;
        this.requiredQty = requiredQty;
        this.usedInRecipes = usedInRecipes;
        updateStatus();
    }

    public void updateStatus() {
        if (currentQty <= 0) {
            this.stockStatus = "Out of Stock";
        } else if (currentQty < requiredQty) {
            this.stockStatus = "Low Stock";
        } else {
            this.stockStatus = "Sufficient";
        }
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getCurrentQty() { return currentQty; }
    public double getRequiredQty() { return requiredQty; }
    public String getStockStatus() { return stockStatus; }
    public String getUsedInRecipes() { return usedInRecipes; }

    public void setName(String name) { this.name = name; }
    public void setUsedInRecipes(String usedInRecipes) { this.usedInRecipes = usedInRecipes; }

    public void setCurrentQty(double currentQty) {
        this.currentQty = currentQty;
        updateStatus();
    }

    public void setRequiredQty(double requiredQty) {
        this.requiredQty = requiredQty;
        updateStatus();
    }
}