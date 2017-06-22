package main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.ServiceProjectTask;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Configuration
public class RestConfiguration {

    private static final Integer port = 1192;

    private RmiProxyFactoryBean getService(Class<?> type, String name, Integer port) {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(type);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:" + port + "/" + name);
        rmiProxyFactoryBean.afterPropertiesSet();
        return rmiProxyFactoryBean;
    }

    @Bean
    public ServiceProjectTask serviceProjectTask() {
        return (ServiceProjectTask) getService(ServiceProjectTask.class, "ServiceProjectTask", port).getObject();
    }
}
