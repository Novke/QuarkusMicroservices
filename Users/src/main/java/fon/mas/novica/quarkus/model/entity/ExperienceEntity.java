package fon.mas.novica.quarkus.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "experience")
public class ExperienceEntity extends PanacheEntity {
    @Id
    @MapsId
    @OneToOne(optional = false)
    public UserEntity user;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    public Integer count = 0;

    public Integer increase() {
        return ++count;
    }

    public ExperienceEntity() {
    }

    public ExperienceEntity(UserEntity user, Integer count) {
        this.user = user;
        this.count = count;
    }
}
