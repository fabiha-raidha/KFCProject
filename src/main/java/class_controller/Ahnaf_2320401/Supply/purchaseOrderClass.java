package class_controller.Ahnaf_2320401.Supply;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class purchaseOrderClass implements Serializable {
    private String poNumber;
    private String supplier;
    private String deliveryLocation;
    private LocalDate deliveryDate;
    private double total;
    private String status;
    private List<LineItem> lineItems;

    public purchaseOrderClass(String poNumber, String supplier, String deliveryLocation, LocalDate deliveryDate, String status) {
        this.poNumber = poNumber;
        this.supplier = supplier;
        this.deliveryLocation = deliveryLocation;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.lineItems = new ArrayList<>();
        this.total = 0.0;
    }

    public static class LineItem implements Serializable {
        private String ingredient;
        private int quantity;
        private double price;

        public LineItem(String ingredient, int quantity, double price) {
            this.ingredient = ingredient;
            this.quantity = quantity;
            this.price = price;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getLineTotal() {
            return quantity * price;
        }

        @Override
        public String toString() {
            return "LineItem{" +
                    "ingredient='" + ingredient + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    ", lineTotal=" + getLineTotal() +
                    '}';
        }
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
        calculateTotal();
    }

    public void calculateTotal() {
        total = lineItems.stream()
                .mapToDouble(LineItem::getLineTotal)
                .sum();
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
        calculateTotal();
    }

    @Override
    public String toString() {
        return "purchaseOrderClass{" +
                "poNumber='" + poNumber + '\'' +
                ", supplier='" + supplier + '\'' +
                ", deliveryLocation='" + deliveryLocation + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", lineItems=" + lineItems +
                '}';
    }
}
