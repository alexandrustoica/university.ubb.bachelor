package connection;

import error.Error;
import error.Errors;
import response.ResponseProtocol;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseSender implements ResponseSenderProtocol {

    private Socket socket;
    private ObjectOutputStream output;

    public ResponseSender(Socket socket) {
        try {
            this.socket = socket;
            output = new ObjectOutputStream(this.socket.getOutputStream());
            output.flush();
        } catch (IOException error) {
            Errors errors = new Errors();
            errors.add(new Error(error.getMessage()));
            handleErrors(errors);
        }
    }

    private void handleErrors(Errors errors) {
        System.out.println(errors.getMessage());
    }

    @Override
    public synchronized void sendResponse(ResponseProtocol response) throws IOException {
        output.writeObject(response);
        output.flush();
    }

}
