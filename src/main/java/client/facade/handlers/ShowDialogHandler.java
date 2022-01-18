package client.facade.handlers;

import client.facade.ClientFacade;
import client.windows.MainWindow;
import server.state.IllegalCommandException;

public class ShowDialogHandler extends BaseHandler {

    MainWindow mainWindow;

    public ShowDialogHandler(ClientFacade facade) {
        super(facade);
        mainWindow = facade.getMainWindow();
    }

    @Override
    public void setNext(IHandler h) {
        this.next = h;
    }

    @Override
    public void handle(String command) throws IllegalCommandException {
        if(command.startsWith("POPUP")){
            mainWindow.showDiagalog(command.substring(6));
        } else{
            if(next != null) next.handle(command);
        }
    }
}
