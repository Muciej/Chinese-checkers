package server.moves;

import server.board.Board;

import java.util.ArrayList;
import java.util.Objects;

public class MovesMaster {
    Board board;
    ArrayList<IMove> chain;
    ArrayList<Move> availableMoves;

    MovesMaster(Board board){
        this.board = board;
        chain = new ArrayList<>();
        availableMoves = new ArrayList<>();

        //chain.add(new JumpMove());
        chain.add(new SlideMove());
    }

    boolean checkFor(Move move){
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
            System.out.println("Możliwy ruch na: " + m.toX + " " + m.toY);
        }
        return availableMoves.contains(move);
    }


}
