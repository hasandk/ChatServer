import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class ClientHandler extends Thread
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run()
    {
        try{
            String received;
            Scanner scanner = new Scanner(System.in);
            while (true)
            {
                try {
                    System.out.print("you: ");
                    String tosend = scanner.nextLine();

                    // Ask user what he wants
                    dos.writeUTF(tosend);

                    // receive the answer from client
                    received = dis.readUTF();
                    System.out.println("Client: " + received);

                } catch (IOException e) {
                    this.s.close();
                    e.printStackTrace();
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}