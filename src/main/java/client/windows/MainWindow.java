package client.windows;

import client.board.Board;
import client.facade.ClientFacade;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    ClientFacade facade;

    public MainWindow(ClientFacade facade){
        this.facade = facade;
        setTitle("Chinese-checkers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setVisible(true);
    }

    public void showDiagalog(String message){
        showMessageDialog(null, message);
    }
}
