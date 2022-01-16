package server.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {
    Socket socket;
    PrintWriter to_serv;
    Scanner from_serv;
    TestClient(int port){
        try {
            this.socket = new Socket("localhost", port);
            to_serv = new PrintWriter(socket.getOutputStream(), true);
            from_serv = new Scanner(socket.getInputStream());
            System.out.println("connected to server");
        } catch (IOException e) {
            System.out.println("Unable to connect to server");
            e.printStackTrace();
        }
    }

    public String testReceive(){
        return from_serv.nextLine();
    }

    public void testSend(String command){
        System.out.println("Testing send");
        to_serv.println(command);
    }

}
