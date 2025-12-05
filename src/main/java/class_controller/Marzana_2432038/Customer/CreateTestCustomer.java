package class_controller.Marzana_2432038.Customer;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class CreateTestCustomer {

    public static void main(String[] args) {
        try {
            CustomerAccount testCustomer = new CustomerAccount(
                "testcustomer",
                "01700000000",
                "test@kfc.com",
                "Regular",
                "customer123"
            );

            FileOutputStream fos = new FileOutputStream("data/CustomerAccount.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(testCustomer);
            oos.close();

            System.out.println("=== Test Customer Created Successfully ===");
            System.out.println("Login Credentials:");
            System.out.println("  Username: testcustomer (or test@kfc.com)");
            System.out.println("  Password: customer123");
            System.out.println("==========================================");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to create test customer.");
        }
    }
}
