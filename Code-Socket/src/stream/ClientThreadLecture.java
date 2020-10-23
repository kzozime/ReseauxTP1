package stream;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientThreadLecture extends Thread {
	
	/**
	 * Socket du client
	 */
	public Socket socketLecture;
	/**
	 * message entrant sur socIn
	 */
	public String message;
	/**
	 * Nom du client
	 */
	public String name;
	/**
	 * Le constructeur de ThreadLecture prend en parametre un socket et une chaine de caracter qui initialise le nom et le le socket
	 * @param s initialise socketClientLecture
	 * @param log initialise le login
	 */
	ClientThreadLecture(Socket s, String log) {
		this.socketLecture = s;
		this.name = log;
	}
	/**
	 * Méthode appelée au démarrage du thread grâce à la méthode start(). Elle se charge de le lire les informations qui arrivent sur le socIn et les affiche ensuite.
	 */
	public void run() {
		BufferedReader socIn;	  
		try {
	  		socIn = new BufferedReader(new InputStreamReader(socketLecture.getInputStream()));
	  		while(true){
		  		message = socIn.readLine();
		  		System.out.println(message);
	  		}
	  		
	  	} catch (Exception e) {
	      	System.err.println("Error in EchoServer:" + e); 
      }
	  
  }


}

