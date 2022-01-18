package client.facade;

import client.board.Board;
import client.connection.ServerConnect;
import client.facade.handlers.*;
import client.windows.MainWindow;
import client.windows.StartPnl;
import server.state.IllegalCommandException;

import java.awt.*;

public class ClientFacade {

    MainWindow mainWindow;
    StartPnl startPnl;
    Board board;
    IHandler chain;
    String playerName;
    ServerConnect serverConnect;

    public ClientFacade(){
        serverConnect = new ServerConnect("localhost", 60000, this);
        mainWindow = new MainWindow(this);
        startPnl = new StartPnl(this);
        startPnl.setVisible(true);
        board = new Board(this);
        board.setVisible(false);
        mainWindow.add(startPnl, BorderLayout.CENTER);
        mainWindow.add(board, BorderLayout.CENTER);

        IHandler first = new StartHandler(this);
        IHandler second = new MoveHandler(this);
        first.setNext(second);
        IHandler third = new ShowDialogHandler(this);
        second.setNext(third);
        IHandler fourth = new QuitHandler(this);
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
