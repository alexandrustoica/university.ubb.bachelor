package cell;

import domain.Event;
import javafx.scene.control.ListCell;
import service.ClientService;
import utils.ThrowPipe;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EventListCell extends ListCell<Event> {

    private ClientService clientManager;

    public EventListCell(ClientService clientManager) {
        this.clientManager = clientManager;
    }

    private String getCellText(Event item) throws RemoteException {
        return item + " Players: " + clientManager.getPlayerFromEvent(item).size();
    }

    protected void updateItem(Event item, boolean empty) {
        super.updateItem(item, empty);
        ThrowPipe.wrap(() -> setText((item != null && !empty) ? getCellText(item) : null));
    }
}
