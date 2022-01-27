package server.handler;

import server.ChineseCheckerServer;
import server.Player;
import server.board.Board;
import server.state.GameEndState;
import server.state.IllegalCommandException;

/**
 * Handler sprawdzający, czy ktoś nie wygrał
 */
public class CheckHandler extends BaseHandler{

    public CheckHandler(ChineseCheckerServer man){
        super(man);
    }

    @Override
    public void setNext(IHandler h) {
        this.next = h;
    }

    @Override
    public void handle(String command) throws IllegalCommandException {

        //ta metoda wywołuje się niezależnie od przesłanej komendy, jeśli gra w fazie rozgrywki

        Board board = manager.getBoard();
        System.out.println("Win checking");
        for(Player p: manager.getPlayers()){
            if(board.checkPos(p.getName(), p.getDest())){
                manager.sendCommand("ALL POPUP Game ended. Winner is: "+p.getName());
                manager.setCurrentState(new GameEndState(manager));
                break;
            }
        }

        if(next != null){
            next.handle(command);
        }
    }
}
