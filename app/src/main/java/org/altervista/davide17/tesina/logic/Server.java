package org.altervista.davide17.tesina.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
	
	private ServerSocket ss;
	private int nextID = 0;
	private ArrayList<SubServer> sockets = new ArrayList<SubServer>();

	//DA SBLOCARE SUL RASPBERRY!!!!!!!!!!!!!!!!
	/*public static void main(String[] args){
		
		System.out.println("Inizio");
		Server server = new Server(9898);
		server.start();
	}*/
	
	public Server(int port) {
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			try {
				System.out.println("Attendo la connessione");
				Socket clientSock = ss.accept();
				sockets.add(new SubServer(clientSock, nextID ++));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class SubServer extends Thread {
		private int id;
		private PrintWriter writer;
	    private BufferedReader reader;
		private Socket socket;
		private boolean isActive = true;
		
		public SubServer(Socket socket, int id){
			try {
				this.id = id;
				this.socket = socket;
				writer = new PrintWriter(socket.getOutputStream(), true);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run(){
			do {
				read();
			} while (isActive);
	    }
		
	    public void read(){
	        try {
	            String read = reader.readLine();
	            //QUI ricevi il testo dal telefono e fai quello che devi fare....
				//Per controllare il messaggio -> if(read.equals("qualcosa")){ fai qualcosa }
	            //write(messaggio) per rispondere
	            write("Dal server: " + read);

				write("1");
				write("2");
				write("3");

	        } catch (IOException e) {
	            //e.printStackTrace();
				System.out.println("Socket con id: " + this.id + " e ip: " + socket.getInetAddress() + " e' stato scolegato");
				isActive = false;
				return;
	        }
	    }
	    
	    public int getID() {
	    	return id;
	    }

	    public void write(String messaggio){
	        writer.println(messaggio);
	    }
	}
}
