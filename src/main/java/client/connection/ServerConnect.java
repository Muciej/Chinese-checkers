package client.connection;

import client.facade.ClientFacade;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Klasa odpowiadająca za połączenie klienta z serwerem
 */
public class ServerConnect {

    ClientFacade facade;

    Socket socket;
    ExecutorService threadPool;
    PrintWriter servWriter;
    ServerReader reader;

    /**
     * Konstruktor klasy
     * @param host - host, pod którym klasa będzie próbować się łączyć
     * @param port - port, przez który będzie podjęta próba nawiązania połączenia
     * @param facade - klasa fasady, służąca do dalszej obsługi żądań
     */
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

    /**
     * Funkcja pozwalająca wysłać komendę do serwera
     * @param command - komenda do wysłania
     */
    public void sendCommand(String command){
        System.out.println("Sending: "+command + " to server.");
        servWriter.println(command);
    }

    /**
     * Funkcja służąca do zakończenia połączenia z serwerem
     */
    public void terminate() {
        threadPool.shutdown();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funkcja zwracająca scanner podłączonego serwera
     * @return - zwraca scanner podłączonego serwera
     */
    public Scanner getScanner(){
        return reader.getScanner();
    }

    /**
     * Klasa pomocnicza operująca na osobnym wątku. Służy do
     * odczytywania inputu z serwera. Jest stworzona w ten sposób, żeby
     * nie blokować głównego wątku
     */
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
