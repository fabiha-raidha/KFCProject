package class_controller.Fabiha_2211214.HeadChef;

import java.io.Serializable;
import java.time.LocalDate;

public class InspectionLog implements Serializable {
    private String type;
    private LocalDate lastCheck;
    private LocalDate nextSchedule;
    private double score;
    private String action;

    public InspectionLog(String type, LocalDate lastCheck, LocalDate nextSchedule, double score, String action) {
        this.type = type;
        this.lastCheck = lastCheck;
        this.nextSchedule = nextSchedule;
        this.score = score;
        this.action = action;
    }

    public String getType() { return type; }
    public LocalDate getLastCheck() { return lastCheck; }
    public LocalDate getNextSchedule() { return nextSchedule; }
    public double getScore() { return score; }
    public String getAction() { return action; }
}