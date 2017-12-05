package consumer;

import server.RemoteStoreService;
import server.StoreService;
import store.store.Store;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserConsumer {

    public static void main(String[] args) {
        StoreService store = new RemoteStoreService(new Store());
        ConsoleConsumer console = new ConsoleConsumer(store);
        console.run();
    }
}
