package client.board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public abstract class Field extends JPanel implements MouseListener {

    int x,y;
    Board board;

    public abstract Color getFigCol();
    public abstract int getStartNo();
    public abstract void setStroke(boolean isStroked);
}
