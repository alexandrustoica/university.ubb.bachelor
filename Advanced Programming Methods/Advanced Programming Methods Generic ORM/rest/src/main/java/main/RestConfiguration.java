package main;

import database.DatabaseGateway;
import database.DatabaseLoader;
import database.DatabaseType;
import domain.*;
import model.*;
import org.springframework.context.annotation.*;

import java.io.File;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@ComponentScan("controller")
@ComponentScan("special")
@Configuration
public class RestConfiguration {

    @Bean
    @Scope("singleton")
    public DatabaseGateway databaseGateway() {
        return new DatabaseLoader(DatabaseType.DEFAULT);
    }

    @Bean
    @Primary
    public Model<UserEntity, Integer> userModel() {
        return new ModelRelational<>(UserEntity.class, databaseGateway());
    }

    @Bean
    public Model<BoardEntity, Integer> boardModel() {
        return new ModelRelational<>(BoardEntity.class, databaseGateway());
    }


    @Bean
    public FileModel fileModel() {
        return new FileModel(new File("./rest/src/main/resources"));
    }

    @Bean
    public ModelManyToMany<ProjectEntity, TaskEntity, ProjectTaskEntity, Integer> projectTaskModel() {
        return new ModelRelationalManyToMany<>(ProjectEntity.class, TaskEntity.class, ProjectTaskEntity.class, databaseGateway());
    }

}
