package main;

import database.DatabaseType;
import database.DatabaseLoader;
import database.DatabaseGateway;
import domain.*;
import model.Model;
import model.ModelManyToMany;
import model.ModelRelational;
import model.ModelRelationalManyToMany;
import org.springframework.context.annotation.*;

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
    public ModelManyToMany<ProjectEntity, TaskEntity, ProjectTaskEntity, Integer> projectTaskModel() {
        return new ModelRelationalManyToMany<>(ProjectEntity.class, TaskEntity.class, ProjectTaskEntity.class, databaseGateway());
    }
}
