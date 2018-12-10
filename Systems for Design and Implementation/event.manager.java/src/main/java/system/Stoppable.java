package system;

import org.apache.thrift.TException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Stoppable {
    void stop() throws TException;
}
