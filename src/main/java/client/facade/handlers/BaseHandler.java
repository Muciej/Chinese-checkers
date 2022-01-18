package client.facade.handlers;

import client.facade.ClientFacade;

public abstract class BaseHandler implements IHandler{

    ClientFacade facade;
    IHandler next;

    public BaseHandler(ClientFacade facade){
        this.facade = facade;
        next = null;
    }
}
