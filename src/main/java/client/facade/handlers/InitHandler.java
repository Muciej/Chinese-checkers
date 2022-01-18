package client.facade.handlers;

import client.facade.ClientFacade;
import server.state.IllegalCommandException;

public class InitHandler extends BaseHandler{

    public InitHandler(ClientFacade facade) {
        super(facade);
    }

    @Override
    public void setNext(IHandler h) {
        this.next = h;
    }

    @Override
    public void handle(String command) throws IllegalCommandException {
        if(command.startsWith("")){

        } else{
            if(next != null) next.handle(command);
        }
    }
}
