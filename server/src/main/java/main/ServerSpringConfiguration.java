package main;

import database.ConfigurationType;
import database.DatabaseLoader;
import database.DatabaseSessionGateway;
import domain.*;
import manager.*;
import model.Model;
import model.ModelManyToMany;
import model.ModelRelational;
import model.ModelRelationalManyToMany;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.remoting.rmi.RmiServiceExporter;
import service.*;
import transfarable.User;

import java.io.IOException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Configuration
@ComponentScan("service")
public class ServerSpringConfiguration {

    private final Integer port = 1192;

    @Bean
    @Scope("singleton")
    public DatabaseSessionGateway databaseGateway() {
        return new DatabaseLoader(ConfigurationType.DEFAULT);
    }

    @Bean
    public RmiServiceExporter userService() throws IOException {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        ServiceRelational<User, UserEntity, Integer> service =
                new RelationalManager<>(new ModelRelational<>(UserEntity.class, databaseGateway()));
        rmiServiceExporter.setServiceName("UserService");
        rmiServiceExporter.setService(service);
        rmiServiceExporter.setServiceInterface(ServiceRelational.class);
        rmiServiceExporter.setRegistryPort(port);
        return rmiServiceExporter;
    }

    @Bean
    public Model<UserEntity, Integer> userModel() {
        return new ModelRelational<>(UserEntity.class, databaseGateway());
    }

    @Bean
    public Model<LoggerEntity, Integer> logger() {
        return new ModelRelational<>(LoggerEntity.class, databaseGateway());
    }

    @Bean
    public ModelManyToMany<ProjectEntity, TaskEntity, ProjectTaskEntity, Integer> modelProjectTask() {
        return new ModelRelationalManyToMany<>(ProjectEntity.class, TaskEntity.class, ProjectTaskEntity.class, databaseGateway());
    }

    @Bean
    public RmiServiceExporter loginService() throws IOException {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        LoginService service = new LoginManager(userModel());
        rmiServiceExporter.setServiceName("LoginService");
        rmiServiceExporter.setService(service);
        rmiServiceExporter.setServiceInterface(LoginService.class);
        rmiServiceExporter.setRegistryPort(port);
        return rmiServiceExporter;
    }

    @Bean
    public RmiServiceExporter projectTaskService() throws IOException {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        ServiceProjectTask service = new ProjectTaskManager(modelProjectTask(), center());
        rmiServiceExporter.setServiceName("ServiceProjectTask");
        rmiServiceExporter.setService(service);
        rmiServiceExporter.setServiceInterface(ServiceProjectTask.class);
        rmiServiceExporter.setRegistryPort(port);
        return rmiServiceExporter;
    }

    @Bean
    public RemoteNotificationCenterService center() {
        return new RemoteNotificationCenter();
    }

    @Bean
    public RmiServiceExporter registerService() throws IOException {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        RegisterService service = new RegisterManager(userModel(), logger());
        rmiServiceExporter.setServiceName("RegisterService");
        rmiServiceExporter.setService(service);
        rmiServiceExporter.setServiceInterface(RegisterService.class);
        rmiServiceExporter.setRegistryPort(port);
        return rmiServiceExporter;
    }

    @Bean
    public RmiServiceExporter remoteNotificationCenterService() throws IOException {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        RemoteNotificationCenterService service = center();
        rmiServiceExporter.setServiceName("RemoteNotificationCenterService");
        rmiServiceExporter.setService(service);
        rmiServiceExporter.setServiceInterface(RemoteNotificationCenterService.class);
        rmiServiceExporter.setRegistryPort(port);
        return rmiServiceExporter;
    }

}