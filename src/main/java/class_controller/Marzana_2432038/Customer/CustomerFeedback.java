package class_controller.Marzana_2432038.Customer;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerFeedback implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderId;
    private int rating;
    private String comment;
    private LocalDate date;

    public CustomerFeedback(String orderId, int rating, String comment, LocalDate date) {
        this.orderId = orderId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public String getOrderId() { return orderId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public LocalDate getDate() { return date; }

    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setRating(int rating) { this.rating = rating; }
    public void setComment(String comment) { this.comment = comment; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString() {
        return "CustomerFeedback{" +
                "orderId='" + orderId + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
