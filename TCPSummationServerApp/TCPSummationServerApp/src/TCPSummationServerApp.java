// Part 1 Exercise 5

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPSummationServerApp {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Listening on port 12345...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                
                int num1 = dis.readInt();
                int num2 = dis.readInt();
                int num3 = dis.readInt();
                
                int sum = num1 + num2 + num3;
                int multiplication = num1 * num2 * num3;
                
                dos.writeInt(sum);
                dos.writeInt(multiplication);
                
                dos.flush();
                
                System.out.println("Results sent to the client.");
                
                dis.close();
                dos.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
