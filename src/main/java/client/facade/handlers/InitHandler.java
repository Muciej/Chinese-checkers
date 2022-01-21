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

        //wzór komendy
        /**
         * ALL INIT width height occupiedstartfieldno
         */

        if(command.startsWith("INIT")){
            String[] tab = command.split(" ");
            if(tab.length < 4) throw new IllegalCommandException("Wrong format of command INIT");
            facade.getBoard().initBoard(facade.getScanner(), Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3]));
            facade.setBoardView();

        } else{
            if(next != null) next.handle(command);
        }
    }
}
