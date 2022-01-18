package client.facade.handlers;

import client.board.Board;
import client.facade.ClientFacade;
import server.state.IllegalCommandException;

public class MoveHandler extends BaseHandler{

    Board board;

    public MoveHandler(ClientFacade facade) {
        super(facade);
        board = facade.getBoard();
    }

    @Override
    public void setNext(IHandler h) {
        this.next = h;
    }

    @Override
    public void handle(String command) throws IllegalCommandException {

        //wzór komendy MOVE fromX fromy toX toY

        if(command.startsWith("MOVE")){
            String[] tab = command.split(" ");
            if(tab.length < 5) throw new IllegalCommandException("Zły format komendy MOVE");
            int fromX, fromY, toX, toY;
            fromX = Integer.parseInt(tab[1]);
            fromY = Integer.parseInt(tab[2]);
            toX = Integer.parseInt(tab[3]);
            toY = Integer.parseInt(tab[4]);
            board.doMove(fromX, fromY, toX, toY);

        } else{
            if(next != null) next.handle(command);
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
