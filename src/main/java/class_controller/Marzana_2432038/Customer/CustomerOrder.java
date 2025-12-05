package class_controller.Marzana_2432038.Customer;

import java.io.Serializable;

public class CustomerOrder implements Serializable {

    private String itemName;
    private int quantity;
    private float unitPrice;
    private String deliveryAddress;
    private String paymentMethod;

    public CustomerOrder(String itemName,
                         int quantity,
                         float unitPrice,
                         String deliveryAddress,
                         String paymentMethod) {

        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
