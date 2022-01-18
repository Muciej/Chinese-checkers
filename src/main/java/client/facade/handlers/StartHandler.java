package client.facade.handlers;

import client.facade.ClientFacade;
import server.state.IllegalCommandException;

public class StartHandler extends BaseHandler {

    public StartHandler(ClientFacade facade) {
        super(facade);
    }

    @Override
    public void setNext(IHandler h) {

    }

    @Override
    public void handle(String command) throws IllegalCommandException {

    }
}
