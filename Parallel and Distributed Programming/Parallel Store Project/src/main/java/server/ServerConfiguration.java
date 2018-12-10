package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.remoting.rmi.RmiServiceExporter;
import store.store.Store;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"store"})
@EnableJpaRepositories(basePackages = {"store.repository"})
public class ServerConfiguration {

    private final Store store;

    @Autowired
    public ServerConfiguration(final Store store) {
        this.store = store;
    }

    @Bean
    public StoreService storeService() {
        return new ValidatedStore(new RemoteStoreService(store));
    }

    @Bean
    RmiServiceExporter exporter(StoreService store) {
        Class<StoreService> serviceInterface = StoreService.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(store);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(2020);
        return exporter;
    }
}
