package client.facade.handlers;

import client.facade.ClientFacade;

public abstract class BaseHandler implements IHandler{

    ClientFacade facade;

    public BaseHandler(ClientFacade facade){
        this.facade = facade;
    }
}
