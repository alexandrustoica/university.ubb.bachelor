package service;

import system.*;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ClientService
        extends Startable, Stoppable, Service, UserProtocol, Subscriber, Serializable, Subscription { }
