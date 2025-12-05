package class_controller.Marzana_2432038.StoreManager;

import java.io.Serializable;
import java.time.LocalDate;

public class Expense implements Serializable {

    private final String type;
    private final LocalDate date;
    private String note;
    private float amount;

    public Expense(String type, LocalDate date, String note, float amount) {
        this.type = type;
        this.date = date;
        this.note = note;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
