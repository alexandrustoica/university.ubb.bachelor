package manager;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class PortManager implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    private int port;

    @Override
    public void onApplicationEvent(
            EmbeddedServletContainerInitializedEvent event) {
        port = event.getEmbeddedServletContainer().getPort();
    }

    public int getPort() {
        return port;
    }
}
