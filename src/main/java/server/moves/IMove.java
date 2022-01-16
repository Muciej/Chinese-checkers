package server.moves;

import server.board.Board;

import java.util.ArrayList;

public interface IMove {
    void addMoves(Board board, int x, int y, ArrayList<Integer> avalMoves);

}
