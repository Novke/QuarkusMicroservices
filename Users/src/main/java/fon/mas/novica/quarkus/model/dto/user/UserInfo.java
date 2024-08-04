package fon.mas.novica.quarkus.model.dto.user;

import fon.mas.novica.quarkus.model.dto.role.RoleInfo;

public class UserInfo {

    private String firstName;
    private String lastName;
    private String username;
    private RoleInfo role;

    public UserInfo() {
    }

    public UserInfo(String firstName, String lastName, String username, RoleInfo role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleInfo getRole() {
        return role;
    }

    public void setRole(RoleInfo role) {
        this.role = role;
    }
}
