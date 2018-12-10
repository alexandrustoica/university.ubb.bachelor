package main;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ResourceBundle;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SpringBootApplication
public class SpringServer {

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("application");
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = getContext();
        BasicConfigurator.configure();
        System.out.println("Waiting for clients ...");
    }

    private static ConfigurableApplicationContext getContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(SpringServer.class);
        return builder.run();
    }
}
