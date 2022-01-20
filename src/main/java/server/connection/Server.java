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
    ServerSocket servSocket;
    boolean running = true;
    boolean connecting = true;

    public Server(ChineseCheckerServer ccs, int port){
        clients = new ArrayList<>();
        command_handler = ccs;
        try {
            servSocket = new ServerSocket(port);
            threadPool = Executors.newFixedThreadPool(1);
            threadPool.execute(new ClientAdder(servSocket));
            System.out.println("Server start complete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendCommand(String command) {
        while(connecting){
            System.out.print("w");
        }
        System.out.println("In sendCommand. Connected clients: " + clients.size());
        for(ConnectedClient c: clients){
            System.out.println("Sending: "+command);
            c.sendCommand(command);
        }
    }

    @Override
    public void stopServer() {
        running = false;
        for(ConnectedClient c: clients){
            c.stop();
        }
        clients.clear();
        threadPool.shutdown();
    }

    public int connected(){
        return clients.size();
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
            socket = s;
            try {
                scanner = new Scanner(socket.getInputStream());
                writer = new PrintWriter(socket.getOutputStream(), true);
                clients.add(this);
                System.out.println("Client initiated");

            } catch (IOException e) {
                System.out.println("Nie udało się otworzyć kanałów komunikacji dla klienta");
            }
        }

        /**
         * Established in and out stream with client and waits for any commands to
         * be executed
         */
        @Override
        public void run() {
            System.out.println("Client starting");
            connecting = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
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
         * @param command command to send
         */
        public void sendCommand(String command) {
            System.out.println(command);
            writer.println(command);
        }

        public void stop(){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ClientAdder implements Runnable{
        ServerSocket socket;
        ExecutorService pool;

        ClientAdder(ServerSocket socket){
            this.socket = socket;
            pool = Executors.newFixedThreadPool(6);
        }

        @Override
        public void run() {
            try{
                while(running){
                    Socket s = socket.accept();
                    connecting = true;
                    System.out.println("New client connected");
                    ConnectedClient client = new ConnectedClient(s);
                    pool.execute(client);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
