package server.board;

import java.awt.*;

/**
 * Klasa reprezentująca pole na planszy
 */
public class Field {
    private String occupant;
    private final int startFieldNo;
    private Color color;

    /**
     * Konstruktor pola
     * @param startFieldNo - nr pola startowego, 0 jeśli żadne pole startowe
     */
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
