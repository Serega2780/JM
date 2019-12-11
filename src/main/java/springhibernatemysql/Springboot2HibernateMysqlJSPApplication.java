package springhibernatemysql;

import java.util.Collections;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Springboot2HibernateMysqlJSPApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Springboot2HibernateMysqlJSPApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.servlet.context-path", "/"));

        app.run(args);
    }


}
