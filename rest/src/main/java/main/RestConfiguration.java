package main;

import database.DatabaseType;
import database.DatabaseLoader;
import database.DatabaseGateway;
import domain.ProjectEntity;
import domain.ProjectTaskEntity;
import domain.TaskEntity;
import domain.UserEntity;
import model.Model;
import model.ModelManyToMany;
import model.ModelRelational;
import model.ModelRelationalManyToMany;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
    public Model<UserEntity, Integer> userModel() {
        return new ModelRelational<>(UserEntity.class, databaseGateway());
    }
    @Bean
    public ModelManyToMany<ProjectEntity, TaskEntity, ProjectTaskEntity, Integer> projectTaskModel() {
        return new ModelRelationalManyToMany<>(ProjectEntity.class, TaskEntity.class, ProjectTaskEntity.class, databaseGateway());
    }
}
