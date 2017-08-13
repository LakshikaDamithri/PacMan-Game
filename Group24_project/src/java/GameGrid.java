
import java.util.ArrayList;
import java.util.Random;

//canvas for whole game
public class GameGrid {
    
    private final String[] colorsArray = {"B", "R", "G"};
    public static final int NO_OF_DOTS = 30;
    protected final ArrayList<Dots> dotList = new ArrayList<>();
    private final Random random = new Random();
    
    //create canvas
    void createGrid(){
        for (int i=0; i< NO_OF_DOTS ; i++){
            int dotx = random.nextInt(43) + 1;
            int doty = random.nextInt(43) + 1;
            int color = random.nextInt(3);
        
            boolean exist = false;
            for ( Dots d: dotList) {
                if (d.x == dotx && d.y == doty) {
                    exist = true;
                    break;
                } 
            }
            
            if(exist == false){
                //if there is no dot in the random position add the dot
                dotList.add(new Dots(dotx,doty,colorsArray[color]));
            }   
        }
    }
    
    //get dots string    
    public String getDots() {
        int count = 0;
        if(dotList.isEmpty()) return null;
        String s = "[";
        for (Dots d: dotList) {
            s += d;
            if (count != dotList.size() - 1) {
                s += ",";
            }
        count++;
        }
        s += "]";
        return s;
    }
    
}
