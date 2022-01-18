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
        if(command.startsWith("INIT")){
            String[] tab = command.split(" ");
            if(tab.length < 5) throw new IllegalCommandException("Wrong format of command INIT");

        } else{
            if(next != null) next.handle(command);
        }
    }
}
