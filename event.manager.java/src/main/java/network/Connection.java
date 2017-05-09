package network;

import domain.User;
import service.ConnectionProtocol;
import system.Startable;

public interface Connection extends ConnectionProtocol, Startable, Observer {
    void setUser(User user);
    User getUser();
}
