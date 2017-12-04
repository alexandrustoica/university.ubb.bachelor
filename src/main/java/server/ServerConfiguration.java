package server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import store.domain.Store;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Configuration
@ComponentScan("store.domain")
public class ServerConfiguration {

    private final Store store = new Store();

    @Bean
    public StoreService store() {
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
