package transferable;

import domain.Player;

import java.util.ArrayList;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        04/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class GetPlayerResponse implements ResponseProtocol {

    private ArrayList<Player> listPlayers;

    public GetPlayerResponse(ArrayList<Player> listPlayers) {
        this.listPlayers = listPlayers;

    }

    public ArrayList<Player> getListPlayers() {
        return listPlayers;
    }

    @Override
    public ResponseType getType() {
        return ResponseType.GET_PLAYER;
    }

}
