package client.board;

import javax.swing.*;
import java.awt.*;

public class OccupiedField extends Field{

    final double ocScale = 1.0;

    int startPos;
    Color bcgrndCol, figCol;
    MCircle circle;

    OccupiedField(int startPos, Color bcgrndCol, Color figCol) {
        this.startPos = startPos;
        this.bcgrndCol = bcgrndCol;
        this.figCol = figCol;
        setLayout(new GridLayout(1,1));
        setBackground(this.bcgrndCol);
        circle = new MCircle(figCol, ocScale);
        //add(new JTextField("Occupied"));
        repaint();
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
        ((Graphics2D)g).draw(circle);
    }
}
