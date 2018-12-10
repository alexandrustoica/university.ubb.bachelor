package system;

import org.apache.thrift.TException;

public interface Startable {
    void start() throws TException;
}
