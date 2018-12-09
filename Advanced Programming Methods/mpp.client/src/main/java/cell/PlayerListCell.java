package cell;

import domain.Player;
import javafx.scene.control.ListCell;
import service.ClientService;
import utils.ThrowPipe;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class PlayerListCell extends ListCell<Player> {

    private ClientService clientManager;

    public PlayerListCell(ClientService clientManager) {
        this.clientManager = clientManager;
    }

    private String getCellText(Player item) throws RemoteException {
        return item + " Events: " + clientManager.getEventsFromPlayer(item).size();
    }

    protected void updateItem(Player item, boolean empty) {
        super.updateItem(item, empty);
        ThrowPipe.wrap(() -> setText((item != null && !empty) ? getCellText(item) : null));
    }
}
