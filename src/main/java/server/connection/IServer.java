package server.connection;

public interface IServer {

    void sendCommand(String command);
    void receiveCommands();
}
