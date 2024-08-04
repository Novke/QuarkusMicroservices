package fon.mas.novica.quarkus.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "users")
public class UserEntity extends PanacheEntity {

    @Email(message = "Invalid email")
    @Column(nullable = false, unique = true)
    public String email;
    public String firstName;
    public String lastName;
    @Column(unique = true, nullable = false)
    public String username;
    @Column(nullable = false)
    public String password;
    @ManyToOne(optional = false)
    public RoleEntity role;
    @Column(columnDefinition = "boolean default true")
    public boolean enabled = true;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    public ExperienceEntity experience = new ExperienceEntity(this, 0);

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
}
