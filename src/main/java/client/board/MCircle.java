package client.board;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Pomocnicza klasa rozszerzająca klasę Elipsy
 * Służy jako pionek stojący na polu
 */
public class MCircle extends Ellipse2D.Double {

    Color color;
    final int cHeight = 30;
    final int cWidth = 30;
    boolean isStroked = false;

    /**
     * Konstruktor pionka
     * @param color - kolor pionka
     * @param scale - od 0 do 1, wielkośc pionka w odniesieniu do wielkości pola
     */
    public MCircle(Color color, double scale){
        this.color = color;
        set(scale);
    }

    /**
     * Funkcja odpowiadająca za wypełnienie koła kolorem i narysowanie ewnetualnej krawędzi
     * @param g - obiekt rysujący
     * @see Graphics2D
     */
    public void fill(Graphics2D g){
        g.setPaint(color);
        g.fill(this);
        g.setPaint(new Color(255-color.getRed(), 255-color.getGreen(), 255-color.getBlue()));
        g.setStroke(new BasicStroke(5));
        if(isStroked) g.draw(this);
    }

    /**
     * Funkcja pozwalająca na zmianę skali pionka
     * @param scale - nowa skala
     */
    public void set(double scale){
        setFrame(0, 0, cHeight*scale, cWidth*scale);
    }

    /**
     * Funkcja pozwalająca na zmianę zarówno skali i koloru pionka
     * @param scale - nowa skala
     * @param col - nowy kolor
     */
    public void set(double scale, Color col){
        setFrame(0,0, cHeight*scale, cWidth*scale);
        this.color = col;
    }

    /**
     * Funkcja służąca do ustawienia, czy ma się wyświetlać kontur
     * @param isStroked - czy wyświetlać krawędź
     */
    public void setStroked(boolean isStroked){
        this.isStroked = isStroked;
    }
}
