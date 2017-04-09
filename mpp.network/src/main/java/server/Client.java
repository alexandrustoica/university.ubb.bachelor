package server;

import domain.User;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        09/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Client {

    private Integer orderNumber;
    private User user;

    public Client(Integer orderNumber, User user) {
        this.orderNumber = orderNumber;
        this.user = user;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public User getUser() {
        return user;
    }

}
