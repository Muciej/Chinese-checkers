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
    public ServerConnect(String host, int port, ClientFacade facade){
        this.facade = facade;
        try {
            socket = new Socket(host, port);
            servWriter = new PrintWriter(socket.getOutputStream());
            threadPool = Executors.newFixedThreadPool(1);
            threadPool.execute(new ServerReader(socket, facade));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendCommand(String command){
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
    }
}