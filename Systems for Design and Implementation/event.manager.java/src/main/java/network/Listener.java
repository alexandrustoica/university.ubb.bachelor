package network;

import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import service.Notification;
import service.Subscriber;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Listener extends Observable implements Subscriber.Iface, Runnable {

    private Integer port;

    public Listener(Integer port) {
        this.port = port;
    }

    @Override
    public void update(Notification notification) throws TException {
        notifyObservers(notification);
    }

    @Override
    public void run() {
        Subscriber.Processor processor = new Subscriber.Processor(this);
        TServerTransport serverTransport = null;
        try {
            serverTransport = new TServerSocket(port);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
        server.serve();
        Runnable task = server::serve;
        new Thread(task).start();
    }
}
