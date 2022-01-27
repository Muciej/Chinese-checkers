package server.moves;

import server.board.Board;

import java.util.ArrayList;

/**
 * Interfejs dla klas sprawdzających możliwości wykonania
 * ruchów
 * Klasy implementujące ten interfejs sprawdzają poszczególne typy ruchów
 */
public interface IMove {
    /**
     * Funkcja dodająca wszystkie możliwe ruchy danego typu do tablicy możliwych ruchów
     * @param board - plansza gry
     * @param move - ruch, dla którego pozycji początkowych mają zostać możliwe ruchy
     * @param avalMoves - tablica możliwych ruchów
     */
    void addMoves(Board board, Move move, ArrayList<Move> avalMoves);

}
