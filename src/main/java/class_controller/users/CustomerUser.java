package class_controller.users;

import class_controller.Authenticatable;
import class_controller.Marzana_2432038.Customer.CustomerAccount;
import java.io.*;

public class CustomerUser implements Authenticatable {

    @Override
    public String authenticate(String username, String password) {
        File f = new File("data/CustomerAccount.bin");
        if (!f.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            while (true) {
                try {
                    CustomerAccount c = (CustomerAccount) ois.readObject();
                    if ((c.getName().equalsIgnoreCase(username) || c.getEmail().equalsIgnoreCase(username))
                            && c.getPassword().equals(password)) {
                        return "/class_controller/Marzana_2432038/CustomerFxml/customerDasboard.fxml";
                    }
                } catch (EOFException e) { break; }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public String getRoleName() { return "Customer"; }
}