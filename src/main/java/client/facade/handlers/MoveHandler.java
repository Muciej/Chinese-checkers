package client.facade.handlers;

import client.facade.ClientFacade;
import server.state.IllegalCommandException;

public class MoveHandler extends BaseHandler{

    public MoveHandler(ClientFacade facade) {
        super(facade);
    }

    @Override
    public void setNext(IHandler h) {

    }

    @Override
    public void handle(String command) throws IllegalCommandException {

    }
}
