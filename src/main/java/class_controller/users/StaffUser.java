package class_controller.users;

import class_controller.Authenticatable;

public class StaffUser implements Authenticatable {
    private final String roleName;
    private final String requiredUser;
    private final String requiredPass;
    private final String dashboardPath;

    public StaffUser(String roleName, String requiredUser, String requiredPass, String dashboardPath) {
        this.roleName = roleName;
        this.requiredUser = requiredUser;
        this.requiredPass = requiredPass;
        this.dashboardPath = dashboardPath;
    }

    @Override
    public String authenticate(String username, String password) {
        if (this.requiredUser.equals(username) && this.requiredPass.equals(password)) {
            return dashboardPath;
        }
        return null;
    }

    @Override
    public String getRoleName() {
        return roleName;
    }
}