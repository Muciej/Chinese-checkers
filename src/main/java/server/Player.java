package server;

import java.awt.*;

/**
 * Klasa przechowująca dane na temat gracza
 * Nie obsługuje żadnej logiki
 */
public class Player {
    String name;
    Color color;
    int start;
    int dest;

    public Player(String n, Color c){
        name = n;
        color = c;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getDest() {
        return dest;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }
}
