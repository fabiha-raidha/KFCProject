package class_controller.Ahnaf_2320401.Supply;

import java.io.Serializable;

public class inventoryOverviewClass implements Serializable {
    private String outlet;
    private String ingredient;
    private int currentQty;
    private int minLevel;
    private String stockWarning;

    public inventoryOverviewClass(String outlet, String ingredient, int currentQty, int minLevel, String stockWarning) {
        this.outlet = outlet;
        this.ingredient = ingredient;
        this.currentQty = currentQty;
        this.minLevel = minLevel;
        this.stockWarning = stockWarning;
    }

    public String getOutlet() {
        return outlet;
    }

    public void setOutlet(String outlet) {
        this.outlet = outlet;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(int currentQty) {
        this.currentQty = currentQty;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public String getStockWarning() {
        return stockWarning;
    }

    public void setStockWarning(String stockWarning) {
        this.stockWarning = stockWarning;
    }

    @Override
    public String toString() {
        return "inventoryOverviewClass{" +
                "outlet='" + outlet + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", currentQty=" + currentQty +
                ", minLevel=" + minLevel +
                ", stockWarning='" + stockWarning + '\'' +
                '}';
    }
}

