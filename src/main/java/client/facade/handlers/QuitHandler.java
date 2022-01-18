package client.facade.handlers;

import client.facade.ClientFacade;
import server.state.IllegalCommandException;

public class QuitHandler extends BaseHandler{
    public QuitHandler(ClientFacade facade) {
        super(facade);
    }

    @Override
    public void setNext(IHandler h) {

    }

    @Override
    public void handle(String command) throws IllegalCommandException {

    }
}
