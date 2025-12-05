package class_controller.Marzana_2432038.StoreManager;

import java.io.Serializable;

public class MaintenanceRequest implements Serializable {

    private String issueType;
    private String description;
    private String urgency;

    public MaintenanceRequest(String issueType,
                              String description,
                              String urgency) {
        this.issueType = issueType;
        this.description = description;
        this.urgency = urgency;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }
}
