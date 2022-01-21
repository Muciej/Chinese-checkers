package client.board;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MCircle extends Ellipse2D.Double {

    Color color;
    final int cHeight = 40;
    final int cWidth = 40;

    public MCircle(Color color, double scale){
        this.color = color;
        set(scale);
    }

    public void fill(Graphics2D g){
        g.setPaint(color);
        g.fill(this);
    }

    public void set(double scale){
        setFrame(0, 0, cHeight*scale, cWidth*scale);
    }
}
