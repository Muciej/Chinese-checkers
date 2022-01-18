package client.windows;

import client.facade.ClientFacade;

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
}
