package request;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        10/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RequestReadPlayer implements RequestProtocol {

    private Integer idEvent;

    public RequestReadPlayer() {
        this(0);
    }

    public RequestReadPlayer(Integer idEvent) {
        this.idEvent = idEvent;
    }

    @Override
    public RequestType getType() {
        return RequestType.READ_PLAYER;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

}
