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

    }

    @Override
    public void handle(String command) throws IllegalCommandException {
        if (command.startsWith("START")){

            Board board = manager.getBoard();

            //when start is requested
            if( board.isValidPlayerCount(manager.getPlayerCount())){

                //setting start and destination
                ArrayList<String> positions = board.getPositions(manager.getPlayerCount());
                ArrayList<Player> players = manager.getPlayers();
                int i = 0;
                for(Player p: players){
                    String[] temp = positions.get(i).split("-");
                    p.setStart(Integer.parseInt(temp[0]));
                    p.setDest(Integer.parseInt(temp[1]));
                }

                //setting order of play
                manager.clearPlayOrder();
                int first = (int)( (Math.random()* manager.getPlayerCount()) + 1);




            } else {
                throw new IllegalCommandException("Cannot start game with that amount of players!");
            }

        } else if (next != null) {
            next.handle(command);
        }
    }
}
