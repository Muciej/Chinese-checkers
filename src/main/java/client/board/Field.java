package client.board;

import javax.swing.*;
import java.awt.*;

public abstract class Field extends JPanel {
    public abstract Color getFigCol();
    public abstract int getStartNo();
    public abstract void refreshSize();
}
