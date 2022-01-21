package client.facade;

import client.board.Board;
import client.connection.ServerConnect;
import client.facade.handlers.*;
import client.windows.MainWindow;
import client.windows.StartPnl;
import server.state.IllegalCommandException;

import java.awt.*;
import java.util.Scanner;

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
        board = new Board(this);
        mainWindow.add(startPnl);
        setStartView();
        mainWindow.setVisible(true);

        IHandler first = new StartHandler(this);
        IHandler second = new MoveHandler(this);
        first.setNext(second);
        IHandler third = new ShowDialogHandler(this);
        second.setNext(third);
        IHandler fourth = new QuitHandler(this);
        third.setNext(fourth);
        IHandler fifth = new InitHandler(this);
        fourth.setNext(fifth);
        chain = first;
    }

    public void executeCommand(String command){
        try {
            System.out.println(command);
            if(command.startsWith(playerName)) chain.handle(command.substring(playerName.length()+1));
            if(command.startsWith("ALL")) chain.handle(command.substring(4));
        } catch (IllegalCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendCommand(String command){
        System.out.println(command);
        serverConnect.sendCommand(command);

    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Board getBoard() {
        return board;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void quit() {
        serverConnect.terminate();
    }

    public void setStartView() {
        mainWindow.remove(board);
        mainWindow.add(startPnl);
        startPnl.setVisible(true);
        board.setVisible(false);
    }

    public void setBoardView(){
        mainWindow.remove(startPnl);
        mainWindow.add(board);
        board.setVisible(true);
        startPnl.setVisible(false);
        mainWindow.setSize(1000, 1000);
        board.refreshSize();
    }

    public Scanner getScanner(){
        return serverConnect.getScanner();
    }
}
