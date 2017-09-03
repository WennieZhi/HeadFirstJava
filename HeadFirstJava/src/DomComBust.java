import java.util.ArrayList;

/**
 * Created by zhiyuan on 8/31/17.
 */
public class DomComBust {
    private GameHelper gameHelper = new GameHelper();
    private ArrayList<DomCom> domComArrayList = new ArrayList<>();
    private int numberOfGuess = 0;

    public void setUpGame(){
        DomCom d1 = new DomCom();
        DomCom d2 = new DomCom();
        DomCom d3 = new DomCom();

        d1.setName("baidu.com");
        d2.setName("ali.com");
        d3.setName("tengxun.com");

        domComArrayList.add(d1);
        domComArrayList.add(d2);
        domComArrayList.add(d3);

        System.out.println("Your goal is to sink 3 DotComs.");
        System.out.println("baidu.com,ali.com,tengxun.com");
        System.out.println("Try to sink them in fewest number of guesses");

        for(DomCom domComToSet:domComArrayList){
            ArrayList<String> newlocation = new ArrayList<>();
            newlocation = gameHelper.placeDotCom(3);
            domComToSet.setLocation(newlocation);
        }
    }

    public void startPlaying(){
        while (!domComArrayList.isEmpty()){
            String userGuess = gameHelper.getUserInput("Enter a guess");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    public void checkUserGuess(String userGuess){
        numberOfGuess++;
        String result = "miss";

        for (DomCom domComToTest:domComArrayList){
            result = domComToTest.checkYouself(userGuess);
            if (result.equals("hit")){
                break;
            }
            if (result.equals("kill")){
                domComArrayList.remove(domComToTest);
                break;
            }
        }
        System.out.println(result);

    }

    public void finishGame(){
        System.out.println("It takes you "+numberOfGuess+" guesses.");
    }

    public static void main(String[] args) {
        DomComBust domComBust = new DomComBust();
        domComBust.setUpGame();
        domComBust.startPlaying();
    }

}
