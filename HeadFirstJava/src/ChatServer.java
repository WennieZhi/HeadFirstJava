import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by zhiyuan on 9/4/17.
 */
public class ChatServer {
    ArrayList clientOutputStreams;

    public class ClientHandler implements Runnable{
        BufferedReader reader ;
        Socket sock;

        public ClientHandler(Socket clientSocket){
            try {
                sock = clientSocket;
                InputStreamReader inputStreamReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(inputStreamReader);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            String message;
            try {
                while ((message=reader.readLine())!=null){
                    System.out.println("read "+message);
                    tellEveryone(message);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public void go(){
        clientOutputStreams = new ArrayList();
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            while (true){
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("get a connection");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void tellEveryone(String message){
        Iterator  it = clientOutputStreams.iterator();
        while (it.hasNext()){
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.go();
    }
}
