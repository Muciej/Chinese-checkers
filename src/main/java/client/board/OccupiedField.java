package client.board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class OccupiedField extends Field{

    final double ocScale = 1.0;

    int startPos;
    Color bcgrndCol, figCol;
    MCircle circle;

    OccupiedField(Board b, int x, int y, int startPos, Color bcgrndCol, Color figCol) {
        board = b;
        this.x = x;
        this.y = y;
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
    public void setStroke(boolean isStroked) {
        circle.setStroked(isStroked);
        repaint();
    }

    @Override
    public void setField(Color fig_col, double scale) {
        this.figCol = fig_col;
        circle.set(scale, fig_col);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        circle.fill((Graphics2D)g);
        //((Graphics2D)g).draw(circle);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        board.unhighlight();
        setStroke(true);
        board.initiateMove(x,y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
