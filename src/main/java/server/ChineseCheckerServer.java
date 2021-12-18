package server;

import server.board.Board;
import server.moves.MovesMaster;
import server.state.AbstractState;

import java.awt.*;
import java.util.ArrayList;

public class ChineseCheckerServer {

    private Board board = null;
    private MovesMaster movesMaster;
    private ArrayList<Player> players = new ArrayList<>();
    private AbstractState currentState;

    ChineseCheckerServer(){
        board = new Board(this, "Chinese-checkers.bd");
    }

    public void executeCommand(String command) {
        try {
            currentState.do_comm(command);
        } catch (server.state.IllegalCommandException e) {
            e.printStackTrace();
        }
    }

    public boolean checkMove(int from, int to){
        return false;
    }

    public void addPlayer(String name, Color color, String startingPos, String dest){
       players.add(new Player(name, color, startingPos, dest));
    }
}
