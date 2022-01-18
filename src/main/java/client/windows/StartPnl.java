package client.windows;

import client.facade.ClientFacade;

import javax.swing.*;

public class StartPnl extends JPanel {
    ClientFacade facade;

    public StartPnl(ClientFacade facade){
        this.facade = facade;
    }
}
