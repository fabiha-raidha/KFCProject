package class_controller.Marzana_2432038.StoreManager;

import java.io.Serializable;

public class InventoryRequest implements Serializable {

    private String itemName,status;
    private int requiredQuantity;

    public InventoryRequest(String itemName, int requiredQuantity, String status) {
        this.itemName = itemName;
        this.requiredQuantity = requiredQuantity;
        this.status = status;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(int requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
