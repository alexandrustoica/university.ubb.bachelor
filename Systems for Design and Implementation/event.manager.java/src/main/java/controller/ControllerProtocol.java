package controller;

import network.Connection;
import org.apache.thrift.TException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ControllerProtocol {
    void initialize();
    void setConnection(Connection connection) throws TException;
}