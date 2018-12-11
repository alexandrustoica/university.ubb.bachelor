package main;

import manager.ServerManager;
import service.ServerService;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Server {

    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "file:./server.policy");
            LocateRegistry.createRegistry(1099);
            ServerManager manager = new ServerManager();
            ServerService service = (ServerService) UnicastRemoteObject.exportObject(manager, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("service", service);
            System.out.print("Server Running ...");
        } catch (RemoteException exception) {
            exception.printStackTrace();
        }
    }
}
