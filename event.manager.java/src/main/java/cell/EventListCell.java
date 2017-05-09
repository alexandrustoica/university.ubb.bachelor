package cell;

import domain.Event;
import javafx.scene.control.ListCell;
import network.Connection;
import utils.ThrowPipe;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EventListCell extends ListCell<Event> {

    private Connection clientManager;

    public EventListCell(Connection clientManager) {
        this.clientManager = clientManager;
    }

    private String getCellText(Event item) throws RemoteException {
        //return item + " Players: " + clientManager.getPlayerFromEvent(item).size();
        return "";
    }

    protected void updateItem(Event item, boolean empty) {
        super.updateItem(item, empty);
        ThrowPipe.wrap(() -> setText((item != null && !empty) ? getCellText(item) : null));
    }
}
