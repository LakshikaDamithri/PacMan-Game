//player class
public class Players{
     int playerID;
     int x;
     int y;
     int score;
     
    public Players(int ID, int xp, int yp, int scorep) {
        this.playerID = ID;
        this.x = xp;
        this.y = yp;
        this.score = scorep;
    }

    //update player position
    public void updatePlayers(int event){
        
        if ( event ==40 && (y<44)){
            y++;
        } else if (event==38 && (y>0)){
            y--;
        } else if(event == 37 && (x > 0)){
            x--;
        } else if( event==39 && (x<44)){
            x++;
        }
            
    }
    
    //player string
    @Override
    public String toString() {
        return "[ \"P"+playerID+"\","+score+" , "+x+" , "+y+"]";
    }
        
    
}
