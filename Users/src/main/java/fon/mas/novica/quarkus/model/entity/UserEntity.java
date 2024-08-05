package fon.mas.novica.quarkus.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
@Table(name = "users")
@UserDefinition
public class UserEntity extends PanacheEntity {

    @Email(message = "Invalid email")
    @Column(nullable = false, unique = true)
    public String email;
    public String firstName;
    public String lastName;
    @Column(unique = true, nullable = false)
    @Username
    public String username;
    @Column(nullable = false)
    @Password
    public String password;
    @ManyToOne(optional = false)
    public RoleEntity role;
    @Column(columnDefinition = "boolean default true")
    public boolean enabled = true;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    public ExperienceEntity experience = new ExperienceEntity(this, 0);

    @Roles
    public String getRoleName(){
        return role.name;
    }

    public UserEntity() {
    }

    public UserEntity(String email, String firstName, String lastName, String username, String password, RoleEntity role, boolean enabled) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }


    //FUNKCIJE

    public static List<UserEntity> findActiveUsers(){
        return list("enabled = ?1", true);
    }

    public static UserEntity findByUsername(String username) {
        return find("username = ?1", username).firstResult();
    }
}
