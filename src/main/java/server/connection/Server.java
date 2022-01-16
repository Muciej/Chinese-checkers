package server.connection;

import server.ChineseCheckerServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server implements IServer {

    ChineseCheckerServer command_handler;
    ArrayList<ConnectedClient> clients;
    ExecutorService threadPool;
    ServerSocket Servsocket;

    public Server(ChineseCheckerServer ccs, int port){
        clients = new ArrayList<>();
        command_handler = ccs;
        try {
            Servsocket = new ServerSocket(port);
            threadPool = Executors.newFixedThreadPool(7);
            threadPool.execute(new ClientAdder(Servsocket));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendCommand(String command) {
        for(ConnectedClient c: clients){
            System.out.println("Sending");
            c.sendCommand(command);
        }
    }

    @Override
    public void stopServer() {
        threadPool.shutdownNow();
    }

    /**
     * Inner class used to maintain connection with
     * each of connected clients.
     * Each objects of this class should work on a different thread
     */
    private class ConnectedClient implements Runnable {

        Socket socket;
        Scanner scanner;
        PrintWriter writer;

        ConnectedClient(Socket s) {
            clients.add(this);
            socket = s;
            try {
                scanner = new Scanner(socket.getInputStream());
                writer = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Established in and out stream with client and waits for any commands to
         * be executed
         */
        @Override
        public void run() {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > 0) {
                    command_handler.addCommandToExecute(line);
                }
            }
            clients.remove(this);
            try {
                socket.close();
                System.out.println("Closed connection with client");
            } catch (IOException e) {
                System.out.println("Error when closing connection with client");
            }
        }

        /**
         * Sends a command to this client
         *
         * @param command command to send
         */
        public void sendCommand(String command) {
            System.out.println("uuu, wysyłanie");
            writer.println(command);
        }
    }

    private class ClientAdder implements Runnable{
        ServerSocket socket;

        ClientAdder(ServerSocket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try{
                while(true){
                    Socket s = socket.accept();
                    threadPool.execute(new ConnectedClient(s));
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
