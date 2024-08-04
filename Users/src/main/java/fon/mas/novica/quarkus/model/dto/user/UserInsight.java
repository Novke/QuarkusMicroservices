package fon.mas.novica.quarkus.model.dto.user;

import fon.mas.novica.quarkus.model.dto.role.RoleInfo;
import fon.mas.novica.quarkus.model.dto.xp.ExperienceInfo;

public class UserInsight {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private RoleInfo role;
    private String email;
    private boolean enabled;
    private ExperienceInfo experience;

    public UserInsight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ExperienceInfo getExperience() {
        return experience;
    }

    public void setExperience(ExperienceInfo experience) {
        this.experience = experience;
    }
}
