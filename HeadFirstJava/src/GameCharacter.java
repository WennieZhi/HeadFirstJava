import java.io.Serializable;

/**
 * Created by zhiyuan on 9/3/17.
 */
public class GameCharacter implements Serializable{
    int power;
    String type;
    String[] weapons;

    public GameCharacter(int p,String t,String[] w){
        power = p;
        type = t;
        weapons = w;
    }

    public int getPower() {
        return power;
    }

    public String getType() {
        return type;
    }

    public String getWeapons() {
        String weaponList = "";
        for (String weapon:weapons){
            weaponList+=weapon+" ";
        }
        return weaponList;
    }
}
