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

	public static int globalId = 1;
	public static LinkedList<ClientThread> listeClients=new LinkedList<ClientThread>();
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
			System.out.println("Server ready..."); 
			while (true) {
				Socket clientSocket = listenSocket.accept();
				System.out.println("Connexion from:" + clientSocket.getInetAddress());
				ClientThread ct = new ClientThread(clientSocket, globalId++);
				listeClients.add(ct);
				ct.start();
			}
		} catch (Exception e) {
			System.err.println("Error in EchoServer:" + e);
		}
     }
     public synchronized static void sendMessage(String message) {
    	synchronized (listeClients){

			for(ClientThread client : listeClients) {
				client.sendMessage(message);
			}
		}

	 }
  }

  
