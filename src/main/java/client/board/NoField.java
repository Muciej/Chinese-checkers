package client.board;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NoField extends Field {

    Color color;

    NoField(Board b, Color bcgrndCol){
        board = b;
        color = bcgrndCol;
        setBackground(color);
    }

    @Override
    public Color getFigCol() {
        return null;
    }

    @Override
    public int getStartNo() {
        return -1;
    }

    @Override
    public void setStroke(boolean isStroked) {

    }

    @Override
    public void setField(Color fig_col, double scale) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
