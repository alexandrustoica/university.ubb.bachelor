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

public class RequestReadEvent implements RequestProtocol {

    private Integer idPlayer;

    public RequestReadEvent() {
        this(0);
    }

    public RequestReadEvent(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    @Override
    public RequestType getType() {
        return RequestType.READ_EVENT;
    }

    public Integer getIdPlayer() {
        return idPlayer;
    }

}
