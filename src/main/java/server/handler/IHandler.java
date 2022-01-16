package server.handler;

import server.state.IllegalCommandException;

public interface IHandler {
    void setNext(IHandler h);
    void handle(String command) throws IllegalCommandException;
}
