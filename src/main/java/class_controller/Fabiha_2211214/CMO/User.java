package class_controller.Fabiha_2211214.CMO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected final int id;
    protected String name;
    protected String email;
    protected String phone;
    protected String gender;
    protected LocalDate dob;

    protected String department;
    protected String designation;
    protected String assignedZone;

    protected String username;
    protected String password;

    protected List<User> dependencies;

    public User(int id, String name, String email, String username, String password, String department, String designation, String gender, LocalDate dob, List<User> dependencies) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.department = department;
        this.designation = designation;
        this.gender = gender;
        this.dob = dob;
        this.dependencies = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartment() {
        return department;
    }

    public String getDesignation() {
        return designation;
    }

    public String getAssignedZone() {
        return assignedZone;
    }

    public List<User> getDependencies() {
        return dependencies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAssignedZone(String assignedZone) {
        this.assignedZone = assignedZone;
    }

    public void addDependency(User user) {
        this.dependencies.add(user);
    }
}
