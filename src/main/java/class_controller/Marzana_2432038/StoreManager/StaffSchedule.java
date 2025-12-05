package class_controller.Marzana_2432038.StoreManager;

import java.io.Serializable;
import java.time.LocalDate;

public class StaffSchedule implements Serializable {

    private String staffId;
    private String name;
    private String role;
    private LocalDate shiftDate;
    private String startTime;
    private String endTime;
    private String status;

    public StaffSchedule(String staffId,
                         String name,
                         String role,
                         LocalDate shiftDate,
                         String startTime,
                         String endTime,
                         String status) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.shiftDate = shiftDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
