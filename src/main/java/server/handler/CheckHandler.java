package server.handler;

import server.ChineseCheckerServer;
import server.Player;
import server.board.Board;
import server.state.GameEndState;
import server.state.IllegalCommandException;

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

        for(Player p: manager.getPlayers()){
            if(board.checkPos(p.getName(), p.getDest())){
                manager.setCurrentState(new GameEndState(manager));
                manager.sendCommand("ALL GAMEEND "+p.getName());
                break;
            }
        }

        if(next != null){
            next.handle(command);
        }else{
            System.out.println("Unrecognized command");
        }
    }
}
