package restclient;

import java.util.Collections;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RestClient {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(RestClient.class);
        app.setDefaultProperties(Collections.singletonMap("server.servlet.context-path", "/"));
        app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
        app.run(args);
    }


}
