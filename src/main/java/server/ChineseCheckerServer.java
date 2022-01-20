package server;

import server.connection.IServer;
import server.connection.Server;
import server.handler.BaseHandler;
import server.board.Board;
import server.handler.IHandler;
import server.state.IllegalCommandException;
import server.state.LobbyState;
import server.state.State;

import java.util.ArrayList;

public class ChineseCheckerServer {

    IServer server;
    Board board;
    ArrayList<Player> players;
    ArrayList<Player> playOrder;
    ArrayList<String> commands;
    Player currentPlayer;
    State currentState;
    IHandler commandHandler;

    ChineseCheckerServer(){
        commands = new ArrayList<>();
        players = new ArrayList<>();
        playOrder = new ArrayList<>();
        currentPlayer = null;
        board = new Board(this, "Chinese-checkers.bd");
        currentState = new LobbyState(this);
        commandHandler =  currentState.getHandler();
        server = new Server(this, 60000);
    }

    private synchronized void executeCommands() {
        try {
            while(commands.size() > 0){
                for(String c: commands){
                    commandHandler.handle(c);
                }
                commands.clear();
            }

        } catch (IllegalCommandException e) {
            sendCommand("ERROR" + e.getMessage());
        }
    }

    public synchronized void addCommandToExecute(String command){
        commands.add(command);
        executeCommands();
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

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<Player> getPlayOrder() {
        return playOrder;
    }
}
