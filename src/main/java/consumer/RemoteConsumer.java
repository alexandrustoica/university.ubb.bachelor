package consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import server.StoreService;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SpringBootApplication
@EnableAutoConfiguration
public class RemoteConsumer {

    @Bean
    RmiProxyFactoryBean service() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://localhost:2020/StoreService");
        rmiProxyFactory.setServiceInterface(StoreService.class);
        return rmiProxyFactory;
    }

    public static void main(String[] args) {
        StoreService service = SpringApplication.run(RemoteConsumer.class, args).getBean(StoreService.class);
        new ConsoleConsumer(service).run();
    }
}
