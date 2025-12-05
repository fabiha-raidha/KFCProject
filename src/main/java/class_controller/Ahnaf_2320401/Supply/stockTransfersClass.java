package class_controller.Ahnaf_2320401.Supply;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class stockTransfersClass implements Serializable {
    private String transferId;
    private String fromOutlet;
    private String toOutlet;
    private String status;
    private List<TransferItem> transferItems;

    public stockTransfersClass(String transferId, String fromOutlet, String toOutlet, String status) {
        this.transferId = transferId;
        this.fromOutlet = fromOutlet;
        this.toOutlet = toOutlet;
        this.status = status;
        this.transferItems = new ArrayList<>();
    }

    public static class TransferItem implements Serializable {
        private String ingredient;
        private int quantity;

        public TransferItem(String ingredient, int quantity) {
            this.ingredient = ingredient;
            this.quantity = quantity;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return ingredient + " (" + quantity + ")";
        }
    }

    public void addTransferItem(TransferItem item) {
        transferItems.add(item);
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }


    public String getFromOutlet() {
        return fromOutlet;
    }

    public void setFromOutlet(String fromOutlet) {
        this.fromOutlet = fromOutlet;
    }

    public String getToOutlet() {
        return toOutlet;
    }

    public void setToOutlet(String toOutlet) {
        this.toOutlet = toOutlet;
    }

    public String getItems() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < transferItems.size(); i++) {
            sb.append(transferItems.get(i).toString());
            if (i < transferItems.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public List<TransferItem> getTransferItems() {
        return transferItems;
    }

    public void setTransferItems(List<TransferItem> transferItems) {
        this.transferItems = transferItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "stockTransfersClass{" +
                "transferId='" + transferId + '\'' +
                ", fromOutlet='" + fromOutlet + '\'' +
                ", toOutlet='" + toOutlet + '\'' +
                ", status='" + status + '\'' +
                ", transferItems=" + transferItems +
                '}';
    }
}