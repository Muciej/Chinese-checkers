package server.moves;

import server.board.Board;

import java.util.ArrayList;

public interface IMove {
    /**
     * Funkcja dodająca wszystkie możliwe ruchy danego typu do tablicy możliwych ruchów
     * @param board - plansza gry
     * @param move - ruch, dla którego pozycji początkowych mają zostać możliwe ruchy
     * @param avalMoves - tablica możliwych ruchów
     */
    void addMoves(Board board, Move move, ArrayList<Move> avalMoves);

}
