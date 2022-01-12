package server;

import server.connection.IServer;
import server.connection.Server;
import server.handler.BaseHandler;
import server.board.Board;
import server.state.IllegalCommandException;
import server.state.LobbyState;
import server.state.State;

import java.util.ArrayList;

public class ChineseCheckerServer {

    IServer server;
    Board board;
    ArrayList<Player> players;
    ArrayList<Player> playOrder;
    Player currentPlayer;
    State currentState;
    BaseHandler commandHandler;

    ChineseCheckerServer(){
        server = new Server(this, 55000, "localhost");
        players = new ArrayList<>();
        playOrder = new ArrayList<>();
        currentPlayer = null;
        board = new Board(this, "Chinese-checkers.bd");
        currentState = new LobbyState(this);
    }

    public void executeCommand(String command) {
        try {
            commandHandler.handle(command);
        } catch (IllegalCommandException e) {
            sendCommand("ERROR" + e.getMessage());
        }
    }

    public void sendCommand(String command){
        server.sendCommand(command);
    }

    public void setCurrentState(State newState){
        currentState = newState;
    }

    public int getPlayerCount(){
        return players.size();
    }

    public Player getPlayer(String name){
        Player temp = null;
        for(Player p: players){
            if(p.getName().equals(name)){
                temp = p;
            }
        }
        return temp;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void clearPlayOrder(){
        playOrder = new ArrayList<>();
    }

    public void addToPlayorder(Player p){
        playOrder.add(p);
    }
}
