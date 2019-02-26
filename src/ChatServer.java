import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

    public static void main(String[] args) {
        new ChatServer().runServer();
    }

    public void runServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(1337);
            Socket socket;
            //send til klient

            while(true){
                socket = null;
                try{
                    socket = serverSocket.accept();
                    System.out.println("forbundet til Klienten");

                    // obtaining input and out streams
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                    System.out.println("Assigning new thread for this client");

                    // create a new thread object
                    Thread t = new ClientHandler(socket, dis, dos);

                    // Invoking the start() method
                    t.start();
                }catch (Exception ex){
                    socket.close();
                    throw ex;
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
