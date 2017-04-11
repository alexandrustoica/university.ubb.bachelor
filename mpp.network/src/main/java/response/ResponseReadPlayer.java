package response;

import domain.Player;
import error.Errors;

import java.util.ArrayList;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        10/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseReadPlayer implements ResponseProtocol {

    private ArrayList<Player> players;

    private Errors errors;

    public ResponseReadPlayer(Errors errors) {
        this.errors = errors;
    }

    public ResponseReadPlayer(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public ResponseType getType() {
        return ResponseType.READ_PLAYER;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Errors getErrors() {
        return errors;
    }

}
