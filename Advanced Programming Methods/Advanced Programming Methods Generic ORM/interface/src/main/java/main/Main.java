package main;

import javafx.application.Application;
import javafx.stage.Stage;
import manager.StageManager;
import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import view.ViewType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SpringBootApplication
@SuppressWarnings("all")
public class Main extends Application {

    public ConfigurableApplicationContext context;
    private StageManager stageManager;

    public static void main(final String[] args) {
        BasicConfigurator.configure();
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        context = getContext();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stageManager = context.getBean(StageManager.class, stage);
        displayScene();
    }

    protected void displayScene() {
        stageManager.switchScene(ViewType.LOGIN);
    }

    @Override
    public void stop() throws Exception {
        context.close();
        System.exit(0);
    }

    public ConfigurableApplicationContext getContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        String[] args = {};
        builder.headless(false); // needed for TestFX integration
        return builder.run(args);
    }

    @Bean
    public ExecutorService executorService() {
        String threadNamePrefix = "ApplicationThread-";
        return Executors.newFixedThreadPool(5, new CustomizableThreadFactory(threadNamePrefix));
    }
}