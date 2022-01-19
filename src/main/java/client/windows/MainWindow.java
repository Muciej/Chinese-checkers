package client.windows;

import client.board.Board;
import client.facade.ClientFacade;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private ClientFacade facade;
    private final Color bcgrndCol = new Color(85, 166, 224);

    public MainWindow(ClientFacade facade){
        this.facade = facade;
        setTitle("Chinese-checkers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,1));
        setBackground(bcgrndCol);
        setSize(800, 100);
    }

    public void showDiagalog(String message){
        showMessageDialog(null, message);
    }

    public Color getBcgrndCol() {
        return bcgrndCol;
    }
}
