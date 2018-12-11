package main;

import org.apache.log4j.BasicConfigurator;
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
        BasicConfigurator.configure();
        SpringApplication.run(RestServer.class, args);
    }
}
