package server.moves;

import server.board.Board;

import java.util.ArrayList;

public class SlideMove implements IMove{

    int fromX;
    int fromY;
    String playerName;
    Board board;
    ArrayList<Move> avalMoves;

    @Override
    public void addMoves(Board board, Move move, ArrayList<Move> avalMoves) {
        fromX = move.fromX;
        fromY = move.fromY;
        playerName = move.playerName;
        this.board = board;
        this.avalMoves = avalMoves;

        checkDir(1,1);
        checkDir(1,-1);
        checkDir(-1, -1);
        checkDir(-1, 1);
        checkDir(2,0);
        checkDir(-2, 0);

    }

    private void checkDir(int dx, int dy){
        int currX = fromX + dx;
        int currY = fromY + dy;
        if(currX < board.getWidth() && currX >= 0 && currY < board.getHeight() && currY >= 0 && board.getField(currX, currY) != null && board.getField(currX, currY).getOccupant() == null) {
            avalMoves.add(new Move(playerName, fromX, fromY, currX, currY));
        }
    }
}
