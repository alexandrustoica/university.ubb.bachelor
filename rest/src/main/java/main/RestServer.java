package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@ComponentScan
@EnableAutoConfiguration
public class RestServer {

    public static void main(String[] args) {
        SpringApplication.run(RestServer.class, args);
    }
}
