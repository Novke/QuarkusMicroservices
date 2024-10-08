package fon.mas.novica.quarkus.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends PanacheEntity {
    public String name;

    public RoleEntity() {
    }

    public RoleEntity(String name) {
        this.name = name;
    }
}
