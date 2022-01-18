package client.facade;

import client.board.Board;
import client.facade.handlers.*;
import client.windows.EndPnl;
import client.windows.MainWindow;
import client.windows.StartPnl;
import server.state.IllegalCommandException;

public class ClientFacade {

    MainWindow mainWindow;
    StartPnl startPnl;
    EndPnl endPnl;
    Board board;
    IHandler chain;

    public ClientFacade(){
        mainWindow = new MainWindow(this);
        startPnl = new StartPnl(this);
        endPnl = new EndPnl(this);
        board = new Board(this);
        IHandler first = new StartHandler();
        IHandler second = new MoveHandler();
        first.setNext(second);
        IHandler third = new ShowDialogHandler();
        second.setNext(third);
        IHandler fourth = new QuitHandler();
        third.setNext(fourth);
        chain = first;
    }

    public void executeCommand(String command){
        try {
            chain.handle(command);
        } catch (IllegalCommandException e) {
            e.printStackTrace();
        }
    }


}
