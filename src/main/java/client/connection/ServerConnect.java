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
    Scanner servScanner;
    PrintWriter servWriter;
    ServerConnect(String host, int port, ClientFacade facade){
        this.facade = facade;
        try {
            socket = new Socket(host, port);
            servScanner = new Scanner(socket.getInputStream());
            servWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
       threadPool = Executors.newFixedThreadPool(1);
       threadPool.execute(new ServerReader(socket, facade));
    }

    public void sendCommand(String command){
        servWriter.println(command);
    }

    private static class ServerReader implements Runnable{

        Socket sock;
        Scanner scanner;
        ClientFacade fac;

        ServerReader(Socket s, ClientFacade facade){
            sock = s;
            fac = facade;
        }

        @Override
        public void run() {
            while(scanner.hasNextLine()){
                fac.executeCommand(scanner.nextLine());
            }
        }
    }
}
