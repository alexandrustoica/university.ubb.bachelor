package main;

import javafx.stage.Stage;
import manager.SpringFXMLLoader;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.LoginService;
import service.RegisterService;
import service.RemoteNotificationCenterService;
import service.ServiceProjectTask;

import java.util.ResourceBundle;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Configuration
@ComponentScan("manager")
@ComponentScan("controller")
@ComponentScan("pagination")
@ComponentScan("center")
@SuppressWarnings("all")
public class SpringConfiguration {

    @Autowired
    private SpringFXMLLoader loader;

    private final static Integer id = 0 + (int) (Math.random() * 100000);

    private StageManager stageManager;

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("application");
    }

    @Bean
    public Integer id() {
        return id;
    }

    @Bean
    @Lazy
    public StageManager stageManager(Stage stage) {
        stageManager = new StageManager(loader, stage);
        return stageManager;
    }

    private static final Integer port = 1192;

    private RmiProxyFactoryBean getService(Class<?> serviceClass, String serviceName, Integer port) {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:" + port + "/" + serviceName);
        rmiProxyFactoryBean.setServiceInterface(serviceClass);
        rmiProxyFactoryBean.afterPropertiesSet();
        return rmiProxyFactoryBean;
    }

    @Bean
    public LoginService loginService() {
        return (LoginService) getService(LoginService.class, "LoginService", port).getObject();
    }

    @Bean
    public ServiceProjectTask serviceProjectTask() {
        return (ServiceProjectTask) getService(ServiceProjectTask.class, "ServiceProjectTask", port).getObject();
    }

    @Bean
    public RegisterService registerService() {
        return (RegisterService) getService(RegisterService.class, "RegisterService", port).getObject();
    }

    @Bean
    public RemoteNotificationCenterService notificationCenter() {
        return (RemoteNotificationCenterService) getService(RemoteNotificationCenterService.class,
                "RemoteNotificationCenterService", port).getObject();
    }
}
