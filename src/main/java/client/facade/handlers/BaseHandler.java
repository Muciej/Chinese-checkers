package client.facade.handlers;

import client.facade.ClientFacade;

/**
 *
 */
public abstract class BaseHandler implements IHandler{

    ClientFacade facade;
    IHandler next;

    /**
     * Konstruktor
     * @param facade - obiekt fasady klienta
     */
    public BaseHandler(ClientFacade facade){
        this.facade = facade;
        next = null;
    }
}
