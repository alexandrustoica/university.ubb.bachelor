package network;

import domain.User;
import service.ConnectionProtocol;
import system.Startable;
import system.Stoppable;

public interface Connection extends ConnectionProtocol, Startable, Stoppable, Observer {
    void setUser(User user);
    User getUser();
    void popObserver(Observer observer);
    void pushObserver(Observer observer);
}
