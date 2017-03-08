package org.altervista.davide17.tesina.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private boolean isActive;
    public OnClientMessageRead onClientMessageRead;

    public Client(String ip, int porta){
        try {
            System.out.println("Client: connessione");
            socket = new Socket(ip, porta);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            start();
            System.out.println("Client partito");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        isActive = true;
        while (isActive){
            read();
        }
    }

    public void read(){
        try {
            String read = reader.readLine();
            System.out.println("Messaggio: " + read);
            if(onClientMessageRead != null)
                onClientMessageRead.onMessageRead (read);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String messaggio){
        System.out.println("Invio: " + messaggio);
        writer.println (messaggio);
        System.out.println(messaggio + " inviato");
    }

    public void stopReceiving(){
        try {
            isActive = false;
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
