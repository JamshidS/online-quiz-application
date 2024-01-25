package com.quiz.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            Scanner clientInput = new Scanner(clientSocket.getInputStream());
            PrintWriter clientOutput = new PrintWriter(clientSocket.getOutputStream(), true);

            while (true) {

                String request = clientInput.nextLine();
                System.out.println("İstemci mesajı: " + request);


                if (request.equals("CONNECT")) {
                    clientOutput.println("CONNECTION_ACCEPTED");
                } else if (request.equals("DISCONNECT")) {
                    clientOutput.println("DISCONNECTED");
                    break;
                } else {
                    clientOutput.println("Bilinmeyen mesaj");
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
