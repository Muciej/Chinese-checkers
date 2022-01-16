package server.connection;

public interface IServer {

    /**
     * Function that allows sending commands to ALL users
     * @param command should have structure of:
     *                user_name command parameters
     */
    void sendCommand(String command);
    void stopServer();
}
