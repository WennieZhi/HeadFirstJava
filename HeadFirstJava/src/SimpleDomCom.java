/**
 * Created by zhiyuan on 8/30/17.
 */
public class SimpleDomCom {
    private int[] locationCells;
    private int numberOfHits=0;

    // check if user's guess matches the location
    public String checkYouself(String guess){
        boolean match = false;
        int guessNum = Integer.parseInt(guess);
        for (int cell:locationCells){
            if (guessNum==cell){
                if (numberOfHits==2){
                    System.out.println("Kill");
                    match = true;
                    int[] locationCellsKill = {};
                    setLocationCells(locationCellsKill);
                    return "Kill";
                }else {
                    numberOfHits++;
                    setNumberOfHits(numberOfHits);
                    setLocationCells(removeCell(locationCells,cell));
                    match = true;
                    System.out.println("Hit");
                    return "Hit";
                }
            }
        }
        if (!match){
            System.out.println("Miss");
            return "Miss";
        }
        return "";
    }

    public void setLocationCells(int[] locations){
        locationCells = locations;
    }

    public void setNumberOfHits(int num){
        numberOfHits = num;
    }
    public int getNumberOfHits(){
        return numberOfHits;
    }

    public int[] removeCell(int[] locations,int cell){
        int[] removedCell = new int[3];
        for (int i=0;i<locations.length;i++){
            if (locations[i]==cell){

            }else {
                removedCell[i]=locations[i];
            }
        }
        return removedCell;
    }

}
