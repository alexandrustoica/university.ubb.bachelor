package cell;

import domain.Player;
import javafx.scene.control.ListCell;
import network.Connection;
import org.apache.thrift.TException;
import utils.ThrowPipe;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class PlayerListCell extends ListCell<Player> {

    private Connection clientManager;

    public PlayerListCell(Connection clientManager) {
        this.clientManager = clientManager;
    }

    private String getCellText(Player item) throws TException {
        return item + " Events: " + clientManager.getEventsFromPlayer(item).size();
    }

    protected void updateItem(Player item, boolean empty) {
        super.updateItem(item, empty);
        ThrowPipe.wrap(() -> setText((item != null && !empty) ? getCellText(item) : null));
    }
}
