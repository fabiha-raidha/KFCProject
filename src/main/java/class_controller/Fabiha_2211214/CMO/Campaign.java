package class_controller.Fabiha_2211214.CMO;

import java.io.Serializable;
import java.time.LocalDate;

public class Campaign implements Serializable {
    private String campaignName;
    private String targetRegion;
    private double budget;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public Campaign(String campaignName, String targetRegion, double budget, LocalDate startDate, LocalDate endDate, String status) {
        this.campaignName = campaignName;
        this.targetRegion = targetRegion;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getCampaignName() { return campaignName; }
    public String getTargetRegion() { return targetRegion; }
    public double getBudget() { return budget; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getStatus() { return status; }
}
