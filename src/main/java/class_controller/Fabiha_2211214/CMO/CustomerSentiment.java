package class_controller.Fabiha_2211214.CMO;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerSentiment implements Serializable {
    private String feedbackId;
    private String customerName;
    private String region;
    private int rating;
    private String comment;
    private LocalDate date;
    private String sentiment;

    public CustomerSentiment(String feedbackId, String customerName, String region, int rating, String comment, LocalDate date) {
        this.feedbackId = feedbackId;
        this.customerName = customerName;
        this.region = region;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
        this.sentiment = calculateSentiment(rating);
    }

    private String calculateSentiment(int rating) {
        if (rating >= 4) return "Positive";
        if (rating == 3) return "Neutral";
        return "Negative";
    }

    public String getFeedbackId() { return feedbackId; }
    public String getCustomerName() { return customerName; }
    public String getRegion() { return region; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public LocalDate getDate() { return date; }
    public String getSentiment() { return sentiment; }
}