package server.handler;

import server.ChineseCheckerServer;
import server.Player;
import server.board.Board;
import server.board.Field;
import server.state.GameStartedState;
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
            int playersNum = manager.getPlayerCount();
            //when start is requested
            if( board.isValidPlayerCount(playersNum)){

                //setting start and destination
                int[] startPositions = board.getPositions(playersNum, "START");
                int[] destPositions = board.getPositions(playersNum, "DEST");
                ArrayList<Player> players = manager.getPlayers();
                manager.clearPlayOrder();
                int i=0;
                for(Player p: players){
                    p.setStart(startPositions[i]);
                    p.setDest(destPositions[i]);
                    manager.addToPlayorder(p);
                    board.fillPos(p.getName(), p.getStart(), p.getColor());
                    i++;
                }
                manager.setCurrentPlayer(manager.getPlayOrder().get(0));

                //kod odpowiedzialny za przekazanie początkowego stanu planszy do klientów
                manager.sendCommand("ALL INIT "+board.getWidth() + " " + board.getHeight()+ " "+ manager.getPlayerCount());
                for(Player p: manager.getPlayers()){
                    String line = null;
                    line = p.getStart() + " ";
                    line += p.getColor().getRed() + " ";
                    line += p.getColor().getGreen() + " ";
                    line += p.getColor().getBlue() + " ";
                    manager.sendCommand(line);
                }

                StringBuilder line;
                for(i=0; i<board.getHeight(); i++){
                    line = new StringBuilder();
                    for(int j=0; j<board.getWidth(); j++){
                        Field field = board.getField(j, i);
                        if(field != null){
                            line.append(field.getStartFieldNo());
                            line.append(' ');
                        } else {
                            line.append("* ");
                        }
                    }
                    manager.sendCommand(line.toString());
                }
                System.out.println("Initialization finished");
                manager.setCurrentState(new GameStartedState(manager));

            } else {
                throw new IllegalCommandException("Cannot start game with that amount of players!");
            }

        } else if (next != null) {
            next.handle(command);
        }else{
            System.out.println("Unrecognized command");
        }
    }
}
