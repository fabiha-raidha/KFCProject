package class_controller.Ahnaf_2320401.Quality;

import java.io.Serializable;
import java.time.LocalDate;

public class trainingComplianceClass implements Serializable {
    private String staffMember;
    private String module;
    private LocalDate dueDate;
    private String status;

    public trainingComplianceClass(String staffMember, String module, LocalDate dueDate, String status) {
        this.staffMember = staffMember;
        this.module = module;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getStaffMember() {
        return staffMember;
    }

    public void setStaffMember(String staffMember) {
        this.staffMember = staffMember;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "trainingComplianceClass{" +
                "staffMember='" + staffMember + '\'' +
                ", module='" + module + '\'' +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                '}';
    }
}

