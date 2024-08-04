package fon.mas.novica.quarkus;

import fon.mas.novica.quarkus.model.entity.RoleEntity;
import fon.mas.novica.quarkus.model.entity.UserEntity;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class OnStartTasks {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public void onStart(@Observes StartupEvent event) {
        if (RoleEntity.count() == 0){
            log.info("Generating roles...");
            RoleEntity.persist(List.of(
                    new RoleEntity("ROLE_USER"),
                    new RoleEntity("ROLE_ADMIN")
                    )
            );
        }
        if (UserEntity.count() == 0){
            log.info("Generating users...");
            UserEntity.persist(List.of(
                    new UserEntity(
                            "default@email.com",
                            "name",
                            "lastname",
                            "user",
//                            encoder().encode("pass"),
                            "pass",
                            RoleEntity.findById(1L),
                            true),
                    new UserEntity(
                            "admin@gmail.com",
                            "Novica",
                            "Trifkovic",
                            "admin",
//                            encoder().encode("admin"),
                            "admin",
                            RoleEntity.findById(2L),
                            true
                    )

            ));
        }
    }

}
