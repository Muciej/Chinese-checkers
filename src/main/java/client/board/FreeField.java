package client.board;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class FreeField extends Field{

    final double freeScale = 0.7;
    final Color freeColor = Color.black;

    int startPos;
    Color bcgrndCol;
    MCircle circle;

    FreeField(int startPos, Color bcgrndCol){
        this.startPos = startPos;
        this.bcgrndCol = bcgrndCol;
        setBackground(bcgrndCol);
        setLayout(new GridLayout(1,1));
        circle = new MCircle(freeColor, getHeight(), getWidth(), freeScale);
    }

    @Override
    public Color getFigCol() {
        return null;
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
