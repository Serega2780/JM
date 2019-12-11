package restserver;

import java.util.Collections;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"restclient", "restserver"})
@SpringBootApplication
public class RestServer {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(RestServer.class);
        app.setDefaultProperties(Collections.singletonMap("server.servlet.context-path", "/"));
        app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        app.run(args);
    }


}
