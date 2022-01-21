package server.handler;


import server.ChineseCheckerServer;
import server.moves.Move;
import server.moves.MovesMaster;
import server.state.IllegalCommandException;

public class MoveHandler extends BaseHandler {

    MovesMaster movesMaster;

    public MoveHandler(ChineseCheckerServer man) {
        super(man);
        movesMaster = new MovesMaster(man.getBoard());
    }

    @Override
    public void setNext(IHandler h) {
        this.next = h;
    }

    @Override
    public void handle(String command) throws IllegalCommandException {
        if(command.startsWith("MOVE")){

            //wzór komendy MOVE NazwaGracza fromX fromY toX toY

            String[] tab = command.split(" ");
            if (tab.length != 6) throw new IllegalCommandException("Nieprawidłowa składnia komendy MOVE");
            Move move = new Move(tab[1], Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]), Integer.parseInt(tab[5]));
            if(movesMaster.checkFor(move)){
                //zmiana na planszy
                manager.getBoard().doMove(move.fromX, move.fromY, move.toX, move.toY);
                //wysłanie zmiany do klientów
                String toSend = "ALL INTERCHANGE ";
                toSend += move.fromX + " " + move.fromY + " ";
                toSend += move.toX + " " + move.toY;
                manager.sendCommand(toSend);
                manager.nextPlayer();
            } else{
                //informacja o nieprawidłowym ruchu
                manager.sendCommand(tab[0] + " MESSAGE Nie wolno Ci wykonać takiego ruchu");
            }
        }
        else {
            if( next != null) {
                next.handle(command);
            }else{
                System.out.println("Unrecognized command");
            }
        }
    }
}
