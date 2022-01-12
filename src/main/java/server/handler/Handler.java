package server.handler;

import server.state.IllegalCommandException;

public interface Handler {
    void setNext(Handler h);
    void handle(String command) throws IllegalCommandException;
}
