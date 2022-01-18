package server.moves;

import server.board.Board;

import java.util.ArrayList;
import java.util.Objects;

public class MovesMaster {
    Board board;
    ArrayList<IMove> chain;
    ArrayList<Move> availableMoves;

    public MovesMaster(Board board){
        this.board = board;
        chain = new ArrayList<>();
        availableMoves = new ArrayList<>();

        chain.add(new JumpMove());
        chain.add(new SlideMove());
    }

    public boolean checkFor(Move move){
        //sprawdzenie, czy w ogóle gracz, który chce wykonać taki ruch znajduje się na tym polu
        if( !move.playerName.equals(board.getField(move.fromX, move.fromY).getOccupant()) ){
            //System.out.println("A won mi stąd");
            return false;
        }
        availableMoves.clear();
        for(IMove m: chain){
            m.addMoves(board, move, availableMoves);
        }
        for(Move m: availableMoves){
            //System.out.println("Możliwy ruch " + m.playerName + " z " + m.fromX + " " + m.fromY);
            //System.out.println("na: " + m.toX + " " + m.toY);
            if(move.toX == m.toX && move.toY == m.toY) return true;
        }
        return false;
    }


}
