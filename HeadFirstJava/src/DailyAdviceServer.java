/**
 * Created by zhiyuan on 9/4/17.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhiyuan on 9/4/17.
 */
public class DailyAdviceServer {
    String[] adviceList = {"take smaller bites","take larger bites","take mid bites"};
    public void go(){
        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            while (true){
                Socket sock = serverSocket.accept();
                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DailyAdviceServer dailyAdviceServer = new DailyAdviceServer();
        dailyAdviceServer.go();
    }
    private String getAdvice(){
        String advice;
        advice = adviceList[(int)(Math.random()*3)];
        return advice;
    }
}

