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


public class GetPlayerRequest implements RequestProtocol {

    private Integer idEvent;

    public GetPlayerRequest(Integer idEvent) {
        this.idEvent = idEvent;
    }

    @Override
    public RequestType getType() {
        return RequestType.GET_PLAYER;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

}
