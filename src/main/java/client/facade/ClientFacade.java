package client.facade;

import client.board.Board;
import client.connection.ServerConnect;
import client.facade.handlers.*;
import client.windows.EndPnl;
import client.windows.MainWindow;
import client.windows.StartPnl;
import server.state.IllegalCommandException;

import java.awt.*;

public class ClientFacade {

    MainWindow mainWindow;
    StartPnl startPnl;
    EndPnl endPnl;
    Board board;
    IHandler chain;
    String playerName;
    ServerConnect serverConnect;

    public ClientFacade(){
        serverConnect = new ServerConnect("localhost", 60000, this);
        mainWindow = new MainWindow(this);
        startPnl = new StartPnl(this);
        startPnl.setVisible(true);
        endPnl = new EndPnl(this);
        endPnl.setVisible(false);
        board = new Board(this);
        board.setVisible(false);
        mainWindow.add(startPnl, BorderLayout.CENTER);
        mainWindow.add(endPnl);
        mainWindow.add(board);

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

    public void sendCommand(String command){
        serverConnect.sendCommand(playerName + " " + command);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
