package client.board;

import java.awt.*;

public class OccupiedField extends Field{
    int startPos;
    Color bcgrndCol, figCol;

    OccupiedField(int startPos, Color bcgrndCol, Color figCol) {
        this.startPos = startPos;
        this.bcgrndCol = bcgrndCol;
        this.figCol = figCol;
    }

    @Override
    public Color getFigCol() {
        return figCol;
    }

    @Override
    public int getStartNo() {
        return startPos;
    }
}
