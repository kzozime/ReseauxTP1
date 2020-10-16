/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */

package stream;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class EchoServerMultiThreaded  {

	public static LinkedList<ClientThreadEcriture> listeClients=new LinkedList<ClientThreadEcriture>();
	
	public static LinkedList<String> chat = new LinkedList<String>();
	
	/**Chemin relatif vers le fichier qui conserve l'historique de la conversation du serveur*/
	private static final String LOGS_FILE = "stream/historique.log";
	
 	/**
  	* main method
	* @param EchoServer port
  	* 
  	**/
    public static void main(String args[]){
		ServerSocket listenSocket;
		if (args.length != 1) {
			  System.out.println("Usage: java EchoServer <EchoServer port>");
			  System.exit(1);
		}
		try {
			listenSocket = new ServerSocket(Integer.parseInt(args[0])); //port
			getHistory();
			System.out.println("Server ready..."); 
			while (true) {
				Socket clientSocket = listenSocket.accept();
				System.out.println("Connexion from:" + clientSocket.getInetAddress());
				ClientThreadEcriture ct = new ClientThreadEcriture(clientSocket, listeClients, chat);
				listeClients.add(ct);
				ct.start();
			}
		} catch (Exception e) {
			System.err.println("Error in EchoServer:" + e);
		}
     }

	public static void writeHistory(){
		try {
			File logFile = new File(LOGS_FILE);
			PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile, true), true);
			for(String msg : chat){
				logWriter.append(msg + "\n");
			}
			logWriter.close();
				
		} catch (FileNotFoundException e) {
			System.out.println("Error : fichier introuvable");
		}	
		
	}
	
	public static void clearHistory(){
		try {
			File logFile = new File(LOGS_FILE);
			PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile, true), true);
			logWriter.write("");
			logWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error : fichier introuvable");
		}	
		
	}
	
	public static void getHistory(){
		try {
			File logFile = new File(LOGS_FILE);
			BufferedReader logReader = new BufferedReader(new FileReader(logFile));
			String line;
			while((line = logReader.readLine()) != null) {
				chat.add(line);
				System.out.println(line);
			} 
			logReader.close();
		}catch (IOException e) {
			System.out.println("getError : logs are not available");
		}
	}


  }











  
