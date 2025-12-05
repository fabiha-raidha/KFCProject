package class_controller.Marzana_2432038.Customer;

import java.io.Serializable;

public class DeliveryStatus implements Serializable {

    private String orderId;
    private String status;
    private String estimatedTime;
    private String driverInfo;

    public DeliveryStatus(String orderId,
                          String status,
                          String estimatedTime,
                          String driverInfo) {
        this.orderId = orderId;
        this.status = status;
        this.estimatedTime = estimatedTime;
        this.driverInfo = driverInfo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(String driverInfo) {
        this.driverInfo = driverInfo;
    }
}
