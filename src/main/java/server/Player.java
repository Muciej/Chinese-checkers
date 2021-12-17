package server;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    String name;
    Color color;
    String start;
    String dest;

    Player(String n, Color c, String st, String dst){
        name = n;
        color = c;
        start = st;
        dest = dst;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public String getDest() {
        return dest;
    }

    public String getStart() {
        return start;
    }
}
