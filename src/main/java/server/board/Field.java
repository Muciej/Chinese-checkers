package server.board;

import java.awt.*;

public class Field {
    private String occupant;
    private final int startFieldNo;
    private Color color;
    Field(int startFieldNo){
        this.startFieldNo = startFieldNo;
        occupant = null;
        color = Color.BLACK;
    }

    public void setOccupant(String occupant) {
        this.occupant = occupant;
    }

    public String getOccupant() {
        return occupant;
    }

    public int getStartFieldNo() {
        return startFieldNo;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
