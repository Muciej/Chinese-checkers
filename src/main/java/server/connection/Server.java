package server.connection;

import server.ChineseCheckerServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Server implements IServer {

    Socket socket = null;
    Scanner scanner;
    PrintWriter writer;
    ChineseCheckerServer manager;

    public Server(ChineseCheckerServer man, int port, String host) {
        manager = man;
        try {
            socket = new Socket(host, port);
            scanner = new Scanner(socket.getInputStream());
            writer = new PrintWriter( socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendCommand(String command) {
        writer.println(command);
    }

    public void receiveCommands() {
        while( scanner.hasNextLine()){
            manager.executeCommand(scanner.nextLine());
        }
    }
}
