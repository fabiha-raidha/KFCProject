package class_controller.Ahnaf_2320401.Supply;

public class receiveShipmentClass {
    private String item;
    private int orderedQty;
    private int receivedQty;

    public receiveShipmentClass(String item, int orderedQty, int receivedQty) {
        this.item = item;
        this.orderedQty = orderedQty;
        this.receivedQty = receivedQty;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(int orderedQty) {
        this.orderedQty = orderedQty;
    }

    public int getReceivedQty() {
        return receivedQty;
    }

    public void setReceivedQty(int receivedQty) {
        this.receivedQty = receivedQty;
    }

    @Override
    public String toString() {
        return "receiveShipmentClass{" +
                "item='" + item + '\'' +
                ", orderedQty=" + orderedQty +
                ", receivedQty=" + receivedQty +
                '}';
    }
}