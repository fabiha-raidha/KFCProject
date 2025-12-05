package class_controller.Fabiha_2211214.CMO;

import java.io.Serializable;
import java.time.LocalDate;

public class MarketingCampaignClass implements Serializable {
    private static final long serialVersionUID = 1L;

    private String campaignId;
    private String campaignName;
    private String targetRegion;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budget;
    private String status;

    public MarketingCampaignClass(String campaignId, String campaignName, String targetRegion, LocalDate startDate, LocalDate endDate, double budget, String status) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.targetRegion = targetRegion;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.status = status;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getTargetRegion() {
        return targetRegion;
    }

    public void setTargetRegion(String targetRegion) {
        this.targetRegion = targetRegion;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MarketingCampaignClass{" +
                "campaignId='" + campaignId + '\'' +
                ", campaignName='" + campaignName + '\'' +
                ", targetRegion='" + targetRegion + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", budget=" + budget +
                ", status='" + status + '\'' +
                '}';
    }
}

