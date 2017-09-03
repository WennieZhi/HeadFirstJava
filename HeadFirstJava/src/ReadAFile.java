import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by zhiyuan on 9/3/17.
 */
public class ReadAFile {
    public static void main(String[] args) {
        try {
            File file = new File("/Users/zhiyuan/IdeaProjects/HeadFirstJava/src/text.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;
            while ((line = reader.readLine())!=null){
                System.out.println(line);
            }
            reader.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
