package client.board;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MCircle extends Ellipse2D.Double {

    Color color;

    public MCircle(Color color, double pnlheight, double pnlwidth, double scale){
        this.color = color;
        set(pnlheight, pnlwidth, scale);
    }

    public void fill(Graphics2D g){
        g.setPaint(color);
        g.fill(this);
    }

    public void set(double height, double width, double scale){
        setFrame(0, 0, height*scale, width*scale);
    }
}
