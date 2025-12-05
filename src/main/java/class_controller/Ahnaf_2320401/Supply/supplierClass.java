package class_controller.Ahnaf_2320401.Supply;

import java.io.Serializable;

public class supplierClass implements Serializable {
    private String name;
    private String category;
    private String contact;
    private String email;

    public supplierClass(String name, String category, String contact, String email) {
        this.name = name;
        this.category = category;
        this.contact = contact;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "supplierClass{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
