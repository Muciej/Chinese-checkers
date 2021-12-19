package server;

import server.handler.BaseHandler;
import server.board.Board;
import server.state.LobbyState;
import server.state.State;

import java.util.ArrayList;

public class ChineseCheckerServer {

    Board board;
    ArrayList<Player> players;
    ArrayList<String> playOrder;
    Player currentPlayer;
    State currentState;
    BaseHandler commandHandler;

    ChineseCheckerServer(){
        players = new ArrayList<>();
        playOrder = new ArrayList<>();
        currentPlayer = null;
        board = new Board(this, "Chinese-checkers.bd");
        currentState = new LobbyState(this);
    }

    public void executeCommand(String command) {
        commandHandler.handle(command);
    }

    public void setCurrentState(State newState){
        currentState = newState;
    }

    public int getPlayerCount(){
        return players.size();
    }

    public Board getBoard() {
        return board;
    }
}
