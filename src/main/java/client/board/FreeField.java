package client.board;

import java.awt.*;

public class FreeField extends Field{

    int startPos;
    Color bcgrndCol;

    FreeField(int startPos, Color bcgrndCol){
        this.startPos = startPos;
        this.bcgrndCol = bcgrndCol;
    }

    @Override
    public Color getFigCol() {
        return null;
    }

    @Override
    public int getStartNo() {
        return startPos;
    }
}
