package client.board;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Klasa reprezentująca puste pole na planszy, na którym nie można stanąć
 */
public class NoField extends Field {

    Color color;

    /**
     * Konstruktor "nieistniejącego" pola
     * @param b - plansza, do której należy pole
     * @param bcgrndCol - kolor tła planszy oraz pól
     */
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
