/**
 * Created by zhiyuan on 8/30/17.
 */
public class SimpleDomComTestDrive {
    public static void main(String[] args){
        SimpleDomCom game0 = new SimpleDomCom();
        int[] locations = {2,3,4};
        game0.setLocationCells(locations);

        String testResult = "failed";
        String guess = "2";

        if (game0.checkYouself(guess)=="Hit"){
            testResult = "passed";
            System.out.println(testResult);
        }else {
            System.out.println(testResult);
        }


    }
}
