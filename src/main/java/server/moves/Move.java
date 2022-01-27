package server.moves;

/**
 * Klasa służąca do przechowywania infromacji na temat ruchu
 */
public class Move {
    public int fromX;
    public int fromY;
    public int toX;
    public int toY;
    public String playerName;

    public Move(String playerName, int fromX, int fromY, int toX, int toY){
        this.fromX = fromX;
        this.fromY = fromY;
        this.playerName = playerName;
        this.toX = toX;
        this.toY = toY;
    }
}
