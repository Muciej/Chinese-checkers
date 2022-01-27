package client.facade.handlers;

import client.facade.ClientFacade;
import server.state.IllegalCommandException;

/**
 * Hanlder obsługujący komendę QUIT
 */
public class QuitHandler extends BaseHandler{
    /**
     * Konstruktor
     * @param facade - obiekt fasady klienta
     */
    public QuitHandler(ClientFacade facade) {
        super(facade);
    }

    @Override
    public void setNext(IHandler h) {
        this.next = h;
    }

    @Override
    public void handle(String command) throws IllegalCommandException {
        if(command.startsWith("QUIT")){
            facade.quit();
        } else{
            if(next != null) next.handle(command);
        }
    }
}
