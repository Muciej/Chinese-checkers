package client;

import client.facade.ClientFacade;

import javax.swing.*;

/**
 * Klasa służąca jedynie do uruchomienia klienta
 */
public class ClientRun {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientFacade::new);
    }
}
