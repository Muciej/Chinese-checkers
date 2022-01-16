package server.moves;

import server.board.Board;

import java.util.ArrayList;

public class MovesMaster {
    Board board;
    ArrayList<IMove> chain;
    ArrayList<Integer> availableMoves;

    MovesMaster(Board board){
        this.board = board;
        chain = new ArrayList<>();
        availableMoves = new ArrayList<>();

        chain.add(new JumpMove());
        chain.add(new SlideMove());
    }

    boolean checkFor(int x, int y){
        availableMoves.clear();
        for(IMove m: chain){
            m.addMoves(board, x, y, availableMoves);
        }
        return availableMoves.contains(x);
    }


}
