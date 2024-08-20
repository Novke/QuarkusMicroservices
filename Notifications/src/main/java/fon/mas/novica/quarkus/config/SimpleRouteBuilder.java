package fon.mas.novica.quarkus.config;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:start")
                .to("smtp://{{mail.host}}:{{mail.port}}?username={{mail.username}}&password={{mail.password}}&mail.smtp.auth=auth&mail.smtp.starttls.enable=starttls")
                .stop();
    }
}
