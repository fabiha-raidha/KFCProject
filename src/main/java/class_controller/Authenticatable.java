package class_controller;

public interface Authenticatable {
    String authenticate(String username, String password);
    String getRoleName();
}
