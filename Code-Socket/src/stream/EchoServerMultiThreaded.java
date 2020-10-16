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
	public static LinkedList<ClientThreadEcriture> listeClients=new LinkedList<ClientThreadEcriture>();
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
				ClientThreadEcriture ct = new ClientThreadEcriture(clientSocket, listeClients);
				listeClients.add(ct);
				ct.start();
			}
		} catch (Exception e) {
			System.err.println("Error in EchoServer:" + e);
		}
     }

  }











  
