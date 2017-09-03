import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by zhiyuan on 9/3/17.
 */
public class GameSaverTest {
    public static void main(String[] args) {
        GameCharacter one = new GameCharacter(50,"Elf",new String[]{"a","b","c"});
        GameCharacter two = new GameCharacter(200,"Troll",new String[]{"a","b"});
        GameCharacter three = new GameCharacter(120,"Magician",new String[]{"a","b","c","d"});

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("game.ser"));
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        one = null;
        two = null;
        three = null;

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("game.ser"));
            GameCharacter one_ = (GameCharacter) is.readObject();
            GameCharacter two_ = (GameCharacter) is.readObject();
            GameCharacter three_ = (GameCharacter) is.readObject();
            System.out.println(one_.getPower()+" "+one_.getType());
            System.out.println(two_.getPower()+" "+two_.getType());
            System.out.println(three_.getPower()+" "+three_.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
