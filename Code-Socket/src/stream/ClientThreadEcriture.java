package stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;

public class ClientThreadEcriture extends Thread {

    public LinkedList<ClientThreadEcriture> listeClients;

    public Socket socket;

    public PrintStream socOut;

    ClientThreadEcriture(Socket s, LinkedList<ClientThreadEcriture> liste) {
        this.socket = s;
        this.listeClients = liste;

    }

    public void run()
    {

        try {
            BufferedReader socIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            socOut = new PrintStream(socket.getOutputStream());


            while(true){
                message = socIn.readLine();
                for(ClientThreadEcriture client : listeClients)
                {
                    if(client != this)
                    {
                        client.socOut.println(message);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }

    }
}
