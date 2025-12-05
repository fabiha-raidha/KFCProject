package class_controller.Ahnaf_2320401.Quality;

import java.io.Serializable;

public class qualityIncidentClass implements Serializable {
    private String store;
    private String product;
    private String lotNumber;
    private String type;
    private String description;

    public qualityIncidentClass(String store, String product, String lotNumber, String type, String description) {
        this.store = store;
        this.product = product;
        this.lotNumber = lotNumber;
        this.type = type;
        this.description = description;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "qualityIncidentClass{" +
                "store='" + store + '\'' +
                ", product='" + product + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
