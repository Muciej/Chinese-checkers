package server;

import server.handler.BaseHandler;
import server.board.Board;
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
        board = new Board(this, "Chinese-checkers.bd");
    }

    public void executeCommand(String command) {

    }

}
