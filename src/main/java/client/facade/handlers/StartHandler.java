package client.facade.handlers;

import client.facade.ClientFacade;
import client.windows.MainWindow;
import client.windows.StartPnl;
import server.state.IllegalCommandException;

public class StartHandler extends BaseHandler {

    public StartHandler(ClientFacade facade) {
        super(facade);
    }

    @Override
    public void setNext(IHandler h) {
        this.next = h;
    }

    @Override
    public void handle(String command) throws IllegalCommandException {

        //wzór komendy START

        if(command.startsWith("START")){
            facade.setBoardView();
        } else{
            if(next != null) next.handle(command);
        }
    }
}
