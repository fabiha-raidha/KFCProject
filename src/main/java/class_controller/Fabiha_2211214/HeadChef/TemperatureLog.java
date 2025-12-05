package class_controller.Fabiha_2211214.HeadChef;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TemperatureLog implements Serializable {
    private String item;
    private double reading;
    private String timestamp;
    private String status;

    public TemperatureLog(String item, double reading) {
        this.item = item;
        this.reading = reading;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.status = determineStatus(reading);
    }

    private String determineStatus(double temp) {
        if (temp <= 5.0 || temp >= 60.0) {
            return "Safe";
        } else {
            return "Danger Zone";
        }
    }

    public String getItem() { return item; }
    public double getReading() { return reading; }
    public String getTimestamp() { return timestamp; }
    public String getStatus() { return status; }
}