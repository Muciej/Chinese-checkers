package client.facade.handlers;

import server.state.IllegalCommandException;

public interface IHandler {
    void setNext(IHandler h);
    void handle(String command) throws IllegalCommandException;
}
