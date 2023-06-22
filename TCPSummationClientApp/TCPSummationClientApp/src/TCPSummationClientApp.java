// Part 1 Exercise 5

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPSummationClientApp {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 12345);
            
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            
            int num1 = 10;
            int num2 = 5;
            int num3 = 7;
            
            dos.writeInt(num1);
            dos.writeInt(num2);
            dos.writeInt(num3);
            
            dos.flush();
            
            int sum = dis.readInt();
            int multiplication = dis.readInt();
            
            System.out.println("Summation: " + sum);
            System.out.println("Multiplication: " + multiplication);
            
            dis.close();
            dos.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
