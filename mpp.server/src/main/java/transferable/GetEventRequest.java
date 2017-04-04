package transferable;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        04/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class GetEventRequest implements RequestProtocol {

    private Integer idPlayer;

    public Integer getIdPlayer() {
        return idPlayer;
    }

    public GetEventRequest(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    @Override
    public RequestType getType() {
        return RequestType.GET_EVENT;
    }


}
