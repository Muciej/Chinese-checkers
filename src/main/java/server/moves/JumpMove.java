package server.moves;

import server.board.Board;

import java.util.ArrayList;

/**
 * Klasa obsługująca ruch skoku przez inny pionek w dowolnym kierunku
 */
public class JumpMove implements IMove {
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
        checkJump(move.fromX, move.fromY, 1, 1);
        checkJump(move.fromX, move.fromY, 1, -1);
        checkJump(move.fromX, move.fromY, -1, 1);
        checkJump(move.fromX, move.fromY, -1 ,-1);
        checkJump(move.fromX, move.fromY, 2,0);
        checkJump(move.fromX, move.fromY, -2,0);

    }

    /**
     * Funkcja służąca do sprawdzenia pojedynczego skoku w zadanym przez dx i dy kierunku
     * Rekurencyjnie wywołuje się dla znalezionych możliwych pól (skok może być wielokrotny)
     * Uwaga, dx i dy powinny wyznaczać wektor jednostkowy w danym kierunku, aby funkcja działała poprawnie
     * @param dx pozioma składowa wektora
     * @param dy pionowa składowa wektora
     */
    private void checkJump(int startX, int startY, int dx, int dy){
        //System.out.println();
        int currX = startX + dx;
        int currY = startY + dy;
        //System.out.println("Sprawdzam, startuję z pola: "+ currX + " " + currY);
        //sprawdza, czy sąsiednie pole jest zajęte przez jakiś pionek oraz czy w ogóle to sąsiednie pole istnieje
        if(isLegalField(currX, currY) && board.getField(currX, currY).getOccupant() != null){
            currX += dx;
            currY += dy;
            //sprawdza, czy docelowe pole do skoku jest puste i czy w ogóle istnieje
            if(isLegalField(currX, currY) && board.getField(currX, currY).getOccupant() == null){
                Move tempMove = new Move(playerName, fromX, fromY, currX, currY);
                boolean isNew = true;
                for(Move m: avalMoves){
                    if (m.toX == tempMove.toX && m.toY == tempMove.toY) {
                        isNew = false;
                        break;
                    }
                }
                if(isNew){
                    avalMoves.add(tempMove);
                    if (1 != -dx || 1 != -dy) checkJump(currX, currY, 1, 1);
                    if (1 != -dx || -1 != -dy) checkJump(currX, currY, 1, -1);
                    if (-1 != -dx || 1 != -dy) checkJump(currX, currY, -1, 1);
                    if (-1 != -dx || -1 != -dy) checkJump(currX, currY, -1, -1);
                    if (2 != -dx || 0 != -dy) checkJump(currX, currY, 2, 0);
                    if (-2 != -dx || 0 != -dy) checkJump(currX, currY, -2, 0);
                }
            }
        }
    }

    private boolean isLegalField(int x, int y){
        return (x < board.getWidth() && x >= 0 && y < board.getHeight() && y >= 0 && board.getField(x, y) != null);
    }
}
