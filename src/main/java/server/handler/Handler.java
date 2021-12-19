package server.handler;

public interface Handler {
    void setNext(Handler h);
    void handle(String command);
}
