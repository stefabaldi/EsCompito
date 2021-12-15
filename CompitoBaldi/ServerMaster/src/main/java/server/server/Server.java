package server.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

    ServerSocket server = null;
    Socket client = null;
    String StringRV = null;
    String StringMD = null;
    int Biglietti= 5;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    public Server(){
        
    }
    public Socket attendi()
    {
        try 
        {

            String ind = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Server in esecuzione..."+ind);
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream( client.getOutputStream());
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }

    public void comunica() 
    {
        
        try
        {
        outVersoClient.writeBytes(" Benvenuto utente, inviare una richiesta di disponibilità di un biglietto o per il suo acquisto" +'\n');
        for(;;)
        {
        StringRV = inDalClient.readLine();
        
        

        if(Biglietti>0)
        {
            if(StringRV.equals("D"))
            {
                StringMD= " Sono rimasti: " +Biglietti+ " biglietti";
                outVersoClient.writeBytes(StringMD+'\n');
            }
            else if(StringRV.equals("A"))
            {
                Biglietti--;
                StringMD=" Biglietto acquistato, Sono rimasti "+Biglietti+" biglietti "+'\n';
                

                outVersoClient.writeBytes(StringMD+'\n');
            }
        }
        else
        {
            outVersoClient.writeBytes((" Spiacenti, i biglietti sono terminati")+'\n');

            System.out.println(" Server: elaborazione conclusa ...");
            client.close();
        }
        

        
    }
        }
        catch(Exception e)
        {
            
        }
        
    }
}
