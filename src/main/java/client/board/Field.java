package client.board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Klasa reprezentująca pojedyncze pole planszy w aplikacji klienta
 * Pełni rolę graficzną, oraz wykrywa kliknięcia myszy na sobie
 */
public abstract class Field extends JPanel implements MouseListener {

    int x,y;
    Board board;

    /**
     * Funcja zwracająca kolor figury, która stoi na tym polu
     * @return kolor figury stojącej na polu, null, jeśli na polu nie stoi nic
     */
    public abstract Color getFigCol();

    /**
     * Funkcja triggerująca rysowanie obwódki dookoła pola
     * @param isStroked - boolean decydujący czy obwódka ma być rysowana
     */
    public abstract void setStroke(boolean isStroked);

    /**
     * Funkcja umożliwiająca zmianę parametrów pola
     * @param fig_col - kolor figury, która ma teraz stać na tym polu
     * @param scale - od 0 do 1, definiuje wielkość koła reprezentującego pion w stosunku do wielkości pola
     */
    public abstract void setField(Color fig_col, double scale);
}
