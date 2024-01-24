package com.quiz.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class QuizClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Sunucuya bağlandı.");


            Scanner serverInput = new Scanner(socket.getInputStream());
            PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true);


            serverOutput.println("CONNECT");


            String response = serverInput.nextLine();
            System.out.println("Sunucu yanıtı: " + response);


            serverOutput.println("DISCONNECT");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
