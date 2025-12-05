package class_controller.Ahnaf_2320401.Supply;

import java.io.Serializable;

public class itemCatalogClass implements Serializable {
    private int itemId;
    private String itemName;
    private String category;
    private String supplier;
    private String status;

    public itemCatalogClass(int itemId, String itemName, String category, String supplier, String status) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.supplier = supplier;
        this.status = status;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "itemCatalogClass{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", supplier='" + supplier + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}