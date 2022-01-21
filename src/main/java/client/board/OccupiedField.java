package client.board;

import java.awt.*;

public class OccupiedField extends Field{

    final double ocScale = 0.8;

    int startPos;
    Color bcgrndCol, figCol;
    MCircle circle;

    OccupiedField(int startPos, Color bcgrndCol, Color figCol) {
        this.startPos = startPos;
        this.bcgrndCol = bcgrndCol.brighter();
        this.figCol = figCol;
        circle = new MCircle(figCol, getHeight(), getWidth(), ocScale);
        setBackground(bcgrndCol);
    }

    @Override
    public Color getFigCol() {
        return figCol;
    }

    @Override
    public int getStartNo() {
        return startPos;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        circle.fill((Graphics2D)g);
    }
}
