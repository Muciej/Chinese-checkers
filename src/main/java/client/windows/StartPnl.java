package client.windows;

import client.facade.ClientFacade;

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

    public StartPnl(ClientFacade facade){
        this.facade = facade;
        this.setBackground(new Color(184, 5, 255));
        this.setLayout(new GridLayout(1,0, 20,20));
        startBtn = new JButton("Start game");
        startBtn.setActionCommand("START");
        startBtn.addActionListener(this);
        playerBtn = new JButton("Join game");
        playerBtn.setActionCommand("ADD");
        playerBtn.addActionListener(this);
        nameField = new JTextField();
        color = new JTextField();
        add(nameField);
        add(color);
        add(playerBtn);
        add(startBtn);
    }

    void addPlayer(){
        String name = nameField.getText();
        String colStr = color.getText();
        facade.sendCommand("ADD "+name+" "+colStr);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("start".equals(e.getActionCommand())){
            facade.sendCommand("START");
        } else if (e.getActionCommand().equals("ADD")){
            addPlayer();
        }
    }
}
