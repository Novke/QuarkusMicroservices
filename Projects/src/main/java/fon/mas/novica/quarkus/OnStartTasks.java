package fon.mas.novica.quarkus;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class OnStartTasks {

    public void startUp(@Observes StartupEvent event){

    }


}
