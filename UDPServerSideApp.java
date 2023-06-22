// Part 2 Exercise 2

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerSideApp {

    public static void main(String[] args) {
        System.out.println("UDPServer: Waiting for client to send a sentence...");

        // Set up UDP socket to listen on a specific port
        int port = 8081;
        try (DatagramSocket socket = new DatagramSocket(port)) {
            byte[] buffer = new byte[65535]; // Maximum UDP packet size

            while (true) {
                // Receive packet from client
                DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivedPacket);

                // Process received data
                String sentence = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                int vowelCount = countVowels(sentence);
                int consonantCount = countConsonants(sentence);
                int punctuationCount = countPunctuations(sentence);

                // Prepare response data
                String response = "Vowels: " + vowelCount + ", Consonants: " + consonantCount + ", Punctuations: " + punctuationCount;
                byte[] responseData = response.getBytes();

                // Send response back to client
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length,
                        receivedPacket.getAddress(), receivedPacket.getPort());
                socket.send(responsePacket);

                System.out.println("UDPServer: Response sent to client");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int countVowels(String sentence) {
        int count = 0;
        for (char c : sentence.toCharArray()) {
            if (isVowel(c)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private static int countConsonants(String sentence) {
        int count = 0;
        for (char c : sentence.toCharArray()) {
            if (isConsonant(c)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isConsonant(char c) {
        return Character.isLetter(c) && !isVowel(c);
    }

    private static int countPunctuations(String sentence) {
        int count = 0;
        for (char c : sentence.toCharArray()) {
            if (isPunctuation(c)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isPunctuation(char c) {
        return !Character.isLetterOrDigit(c) && !Character.isWhitespace(c);
    }
}
