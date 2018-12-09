package remote;

import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RemoteClient {

    private static final String URL = "http://localhost:8080/remote/players";

    private RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public List getAll() {
        return execute(() -> restTemplate.getForObject(URL, List.class));
    }

}
