package class_controller.Marzana_2432038.StoreManager;

import java.io.Serializable;

public class PerformanceSummary implements Serializable {

    private double totalSales,averageRating,attendancePercentage;

    public PerformanceSummary(double totalSales, double averageRating, double attendancePercentage) {
        this.totalSales = totalSales;
        this.averageRating = averageRating;
        this.attendancePercentage = attendancePercentage;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}
