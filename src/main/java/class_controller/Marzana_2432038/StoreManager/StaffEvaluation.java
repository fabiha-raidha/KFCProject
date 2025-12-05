package class_controller.Marzana_2432038.StoreManager;

import java.io.Serializable;

public class StaffEvaluation implements Serializable {

    private String staffName;
    private double attendancePercentage;
    private double pastRating;
    private int rating;
    private String remark;

    public StaffEvaluation(String staffName,
                           double attendancePercentage,
                           double pastRating,
                           int rating,
                           String remark) {
        this.staffName = staffName;
        this.attendancePercentage = attendancePercentage;
        this.pastRating = pastRating;
        this.rating = rating;
        this.remark = remark;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public double getPastRating() {
        return pastRating;
    }

    public void setPastRating(double pastRating) {
        this.pastRating = pastRating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
