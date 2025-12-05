package class_controller.Marzana_2432038.StoreManager;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerFeedback implements Serializable {

    private LocalDate date;
    private String customerId;
    private String product;
    private int rating;
    private String comment;

    public CustomerFeedback(LocalDate date, String customerId, String product, int rating, String comment) {
        this.date = date;
        this.customerId = customerId;
        this.product = product;
        this.rating = rating;
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
