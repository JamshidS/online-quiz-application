package com.quiz.server;

import com.quiz.handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class QuizServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Sunucu başlatıldı. Port: 12345");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Yeni bir istemci bağlandı.");


                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
