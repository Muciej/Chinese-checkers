package client;

import client.facade.ClientFacade;

import javax.swing.*;

public class ClientRun {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientFacade();
            }
        });
    }
}
