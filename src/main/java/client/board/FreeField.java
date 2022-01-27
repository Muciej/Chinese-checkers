package client.board;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * KLASA NIEUŻYWANA
 */
@Deprecated
public class FreeField extends Field{

    final double freeScale = 0.7;
    final Color freeColor = Color.black;

    int startPos;
    Color bcgrndCol;
    MCircle circle;

    /**
     *
     * Konstruktor, ustawia podstawowe parametry pola
     * @param b - obiekt planszy, na której jest pionek. Używany przy obsłudze eventów myszy
     * @param x - wsp. X pola na planszy
     * @param y - wsp. Y pola na planszy
     * @param startPos - nr startowy przypisany do tego pola
     * @param bcgrndCol - kolor tła, używany także w innych polach i planszy
     * @see Board
     * @see Field
     */
    FreeField(Board b, int x, int y,int startPos, Color bcgrndCol){
        board = b;
        this.x = x;
        this.y = y;
        this.startPos = startPos;
        this.bcgrndCol = bcgrndCol;
        setBackground(this.bcgrndCol);
        setLayout(new GridLayout(1,1));
        circle = new MCircle(freeColor, freeScale);
        //add(new JTextField("Free"));
        repaint();
    }

    @Override
    public Color getFigCol() {
        return null;
    }

    @Override
    public void setStroke(boolean isStroked) {
        circle.setStroked(isStroked);
        repaint();
    }

    @Override
    public void setField(Color fig_col, double scale) {

    }

    /**\
     * Funkcja odpowiadająca za poprawne narysowanie komponentu
     * @param g - obiekt klasy Graphics odp. za rysowanie tego obiektu
     * @see Graphics
     * @see Graphics2D
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        circle.fill((Graphics2D) g);
        //((Graphics2D)g).draw(circle);
    }

    /**
     * Funkcja obsługująca klik myszy
     * @param e - obiekt określający zdarzenie myszy
     */
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
