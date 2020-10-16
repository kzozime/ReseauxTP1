package stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;

public class ClientThreadEcriture extends Thread {

    public LinkedList<ClientThreadEcriture> listeClients;
    
    public LinkedList<String> historique;

    public Socket socket;

    public PrintStream socOut;

    ClientThreadEcriture(Socket s, LinkedList<ClientThreadEcriture> liste, LinkedList<String> h) {
        this.socket = s;
        this.listeClients = liste;
        this.historique = h;

    }

    public void run()
    {

        try {
            BufferedReader socIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            socOut = new PrintStream(socket.getOutputStream());

			for( String msg : historique ){
				socOut.println(msg);
			} 

            while(true){
                message = socIn.readLine();
                if(message == null) break;
				
				if (message.contains("historique")){
					System.out.println("on enregistre le tchat");
					EchoServerMultiThreaded.writeHistory();
				}else if(message.contains("clear")){
					EchoServerMultiThreaded.clearHistory();
					System.out.println("historique effacé");
				}else{
					historique.add(message);
					for(ClientThreadEcriture client : listeClients){
						if(client != this)
						{
							client.socOut.println(message);
						}
					}
				}

                
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }

    }
}
