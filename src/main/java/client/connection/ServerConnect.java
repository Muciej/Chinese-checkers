package client.connection;

import client.facade.ClientFacade;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerConnect {

    ClientFacade facade;

    Socket socket;
    ExecutorService threadPool;
    PrintWriter servWriter;
    ServerReader reader;
    public ServerConnect(String host, int port, ClientFacade facade){
        this.facade = facade;
        try {
            socket = new Socket(host, port);
            servWriter = new PrintWriter(socket.getOutputStream(), true);
            threadPool = Executors.newFixedThreadPool(1);
            reader = new ServerReader(socket,facade);
            threadPool.execute(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendCommand(String command){
        System.out.println("Sending: "+command + " to server.");
        servWriter.println(command);
    }

    public void terminate() {
        threadPool.shutdown();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scanner getScanner(){
        return reader.getScanner();
    }

    private static class ServerReader implements Runnable{

        Socket sock;
        Scanner scanner;
        ClientFacade fac;

        ServerReader(Socket s, ClientFacade facade){
            sock = s;
            fac = facade;
            try {
                scanner = new Scanner(sock.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while(scanner.hasNextLine()){
                fac.executeCommand(scanner.nextLine());
            }
        }

        public Scanner getScanner(){
            return scanner;
        }
    }
}
