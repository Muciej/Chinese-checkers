package client.board;

import java.awt.*;

public class NoField extends Field {

    Color color;

    NoField(Color bcgrndCol){
        color = bcgrndCol;
        setBackground(color.darker());
    }

    @Override
    public Color getFigCol() {
        return null;
    }

    @Override
    public int getStartNo() {
        return -1;
    }
}
