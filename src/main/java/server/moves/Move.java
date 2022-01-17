package server.moves;

public class Move {
    int fromX;
    int fromY;
    int toX;
    int toY;
    String playerName;

    Move(String playerName, int fromX, int fromY, int toX, int toY){
        this.fromX = fromX;
        this.fromY = fromY;
        this.playerName = playerName;
        this.toX = toX;
        this.toY = toY;
    }
}
