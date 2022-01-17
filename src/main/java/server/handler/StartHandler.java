package server.handler;

import server.ChineseCheckerServer;
import server.Player;
import server.board.Board;
import server.state.IllegalCommandException;

import java.util.ArrayList;

public class StartHandler extends BaseHandler{
    public StartHandler(ChineseCheckerServer man) {
        super(man);
    }

    @Override
    public void setNext(IHandler h) {
        this.next = h;
    }

    @Override
    public void handle(String command) throws IllegalCommandException {
        if (command.startsWith("START")){

            Board board = manager.getBoard();

            //when start is requested
            if( board.isValidPlayerCount(manager.getPlayerCount())){

                //setting start and destination
                int[] positions = board.getPositions(manager.getPlayerCount());
                ArrayList<Player> players = manager.getPlayers();
                manager.clearPlayOrder();
                int i = 0;

            } else {
                throw new IllegalCommandException("Cannot start game with that amount of players!");
            }

        } else if (next != null) {
            next.handle(command);
        }
    }
}
