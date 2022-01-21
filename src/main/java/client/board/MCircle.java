package client.board;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MCircle extends Ellipse2D.Double {

    Color color;
    final int cHeight = 30;
    final int cWidth = 30;
    boolean isStroked = false;

    public MCircle(Color color, double scale){
        this.color = color;
        set(scale);
    }

    public void fill(Graphics2D g){
        g.setPaint(color);
        g.fill(this);
        g.setPaint(new Color(255-color.getRed(), 255-color.getGreen(), 255-color.getBlue()));
        g.setStroke(new BasicStroke(5));
        if(isStroked) g.draw(this);
    }

    public void set(double scale){
        setFrame(0, 0, cHeight*scale, cWidth*scale);
    }

    public void set(double scale, Color col){
        setFrame(0,0, cHeight*scale, cWidth*scale);
        this.color = col;
    }

    public void setStroked(boolean isStroked){
        this.isStroked = isStroked;
    }
}
