import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by zhiyuan on 9/4/17.
 */
public class DailyAdviceClient {

    public void go(){
        try {
            Socket s = new Socket("127.0.0.1",4242);
            InputStreamReader inputStreamReader = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String advice = reader.readLine();
            System.out.println(advice);
            reader.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DailyAdviceClient client = new DailyAdviceClient();
        client.go();
    }
}
