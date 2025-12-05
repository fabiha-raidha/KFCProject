package class_controller.Marzana_2432038.Customer;

import java.io.Serializable;

public class CustomerAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final String phone;
    private final String email;
    private final String type;
    private final String password;

    public CustomerAccount(String name, String phone, String email,
                           String type, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.password = password;
    }

    public String getName()     { return name; }
    public String getPhone()    { return phone; }
    public String getEmail()    { return email; }
    public String getType()     { return type; }
    public String getPassword() { return password; }
}
