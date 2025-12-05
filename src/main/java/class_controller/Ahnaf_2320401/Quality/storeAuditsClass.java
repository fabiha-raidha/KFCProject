package class_controller.Ahnaf_2320401.Quality;

import java.io.Serializable;
import java.time.LocalDate;

public class storeAuditsClass implements Serializable {
    private String store;
    private String auditor;
    private LocalDate date;
    private String checklistVersion;
    private String status;

    public storeAuditsClass(String store, String auditor, LocalDate date, String checklistVersion, String status) {
        this.store = store;
        this.auditor = auditor;
        this.date = date;
        this.checklistVersion = checklistVersion;
        this.status = status;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getChecklistVersion() {
        return checklistVersion;
    }

    public void setChecklistVersion(String checklistVersion) {
        this.checklistVersion = checklistVersion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "storeAuditsClass{" +
                "store='" + store + '\'' +
                ", auditor='" + auditor + '\'' +
                ", date=" + date +
                ", checklistVersion='" + checklistVersion + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

