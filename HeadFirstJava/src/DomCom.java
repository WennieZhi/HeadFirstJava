import java.util.ArrayList;

/**
 * Created by zhiyuan on 8/31/17.
 */
public class DomCom {
    private String name;
    private ArrayList<String> location = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(ArrayList<String> location) {
        this.location = location;
    }

    //判断是Hit Kill or Miss
    public String checkYouself(String userInput){
        String result = "miss";
        int index = location.indexOf(userInput);
        if (index>=0){
            location.remove(index);
            if (location.isEmpty()){
                result = "kill";
                System.out.println("You sank "+name);
            }else {
                result = "hit";
            }
        }
        return result;

    }
}
