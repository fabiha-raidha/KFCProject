package class_controller.Ahnaf_2320401.Supply;

import java.io.Serializable;

public class supplierReportClass implements Serializable {
    private String metric;
    private String value;
    private String remarks;

    public supplierReportClass(String metric, String value, String remarks) {
        this.metric = metric;
        this.value = value;
        this.remarks = remarks;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "supplierReportClass{" +
                "metric='" + metric + '\'' +
                ", value='" + value + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}

