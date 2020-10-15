/***
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */

package stream;

import java.io.*;
import java.net.*;

public class ClientThread
	extends Thread {
	
	public Socket clientSocket;
	public int idClient;
	PrintStream socOut;

	ClientThread(Socket s, int id) {
		this.clientSocket = s;
		this.idClient = id;
	}

 	/**
  	* receives a request from client then sends an echo to the client
  	* @param clientSocket the client socket
  	**/
	public void run() {
    	  try {
    		BufferedReader socIn = null;
    		socIn = new BufferedReader(
    			new InputStreamReader(clientSocket.getInputStream()));
    		 socOut = new PrintStream(clientSocket.getOutputStream());
    		while (true) {
    		  String line=socIn.readLine();
    		  if(line.length()>0) {
				  System.out.println(line + " " + this.idClient);
				  EchoServerMultiThreaded.sendMessage(line + " " + this.idClient);
			  }

    		}
    	} catch (Exception e) {
        	System.err.println("Error in EchoServer:" + e); 
        }
       }


	public void sendMessage(String message) {
		socOut.println(message);
	}
}

  
