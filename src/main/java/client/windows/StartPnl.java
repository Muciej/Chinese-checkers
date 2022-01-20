package client.windows;

import client.facade.ClientFacade;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPnl extends JPanel implements ActionListener {
    ClientFacade facade;
    JButton playerBtn;
    JButton startBtn;
    JTextField nameField;
    JTextField color;
    MainWindow mainWindow;

    public StartPnl(ClientFacade facade){
        this.facade = facade;
        this.mainWindow = facade.getMainWindow();
        this.setBackground(mainWindow.getBcgrndCol().brighter());
        this.setLayout(new GridLayout(3,1, 0,0));
        JPanel panel = new JPanel();
        JPanel blankPanel1 = new JPanel();
        blankPanel1.setBackground(mainWindow.getBcgrndCol().brighter());
        JPanel blankPanel2 = new JPanel();
        blankPanel2.setBackground(mainWindow.getBcgrndCol().brighter());
        startBtn = new JButton("Start game");
        startBtn.setActionCommand("START");
        startBtn.addActionListener(this);
        playerBtn = new JButton("Join game");
        playerBtn.setActionCommand("ADD");
        playerBtn.addActionListener(this);
        nameField = new JTextField("Write your name");
        nameField.setText("Muciej");
        color = new JTextField("R G B of your color");
        color.setText("120 210 120");
        panel.setBackground(mainWindow.getBcgrndCol().brighter());
        panel.setLayout(new GridLayout(1,4,20,20));
        panel.add(nameField);
        panel.add(color);
        panel.add(playerBtn);
        panel.add(startBtn);
        this.add(blankPanel1);
        this.add(panel);
        this.add(blankPanel2);
    }

    void addPlayer(){
        String name = nameField.getText();
        String colStr = color.getText();
        facade.setPlayerName(name);
        facade.sendCommand("ADD "+name+" "+colStr);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("START".equals(e.getActionCommand())){
            facade.sendCommand("START");
        } else if (e.getActionCommand().equals("ADD")){
            nameField.setVisible(false);
            color.setVisible(false);
            addPlayer();
        }
    }
}
