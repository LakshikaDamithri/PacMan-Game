//game state to update score and player positions
public class GameState{
    public Players[] PlayersArray;
    public final GameGrid grid;
    
    public GameState() { 
        //initialize the players at the four corners
        PlayersArray = new Players[4];
        
        PlayersArray[0] = new Players(1, 0, 0,0);
        PlayersArray[1] = new Players(2, 44, 0,0);
        PlayersArray[2] = new Players(3, 0, 44,0);
        PlayersArray[3] = new Players(4, 44, 44,0);
        
        //create 30 dots in the canvas
        grid = new GameGrid();
        grid.createGrid();
    }
    
    //update score of each player
    public void updateScore(int player_ID, int event ){
        Players Player = PlayersArray[player_ID-1];
        //update each player position
        Player.updatePlayers(event);

        for ( Dots d: grid.dotList){
            if( Player.x== d.x &&  Player.y == d.y ) {
                  if( (d.color).equals("B")) Player.score +=3;
                  else if ((d.color).equals("R")) Player.score +=2;
                  else Player.score+=1;
                  grid.dotList.remove(d);
            } 
        }
    }
    
    //check for the collisions
    public void collide(int player_ID){
        Players Player = PlayersArray[player_ID-1];
        for(int i=1; i<=4 ;i++){
            if(Player.playerID != i){
                if( Player.x == PlayersArray[i-1].x && Player.y == PlayersArray[i-1].y ) {
                    Player.score -= 3;
                    PlayersArray[i-1].score -=3;
                        
                        if(i==1 || Player.playerID==1){
                            PlayersArray[0].x=0;
                            PlayersArray[0].y=0;
                        }
                        if(i==2 || Player.playerID==2){
                            PlayersArray[1].x=44;
                            PlayersArray[1].y=0;
                        }
                        if(i==3 || Player.playerID==3){
                            PlayersArray[2].x=0;
                            PlayersArray[2].y=44;
                        }
                        if(i==4 || Player.playerID==4){
                            PlayersArray[3].x=44;
                            PlayersArray[3].y=44;
                        } 
                }
            }
         }
    }
    
    //get winner when game is over
    public Players winner(){
    
        Players winner = PlayersArray[0];
        if(grid.dotList.isEmpty()){
            for( int j=1 ; j< 4 ; j++){
                if (winner.score < PlayersArray[j].score) winner = PlayersArray[j];
            }
            return winner;
        } else {
            return null;
        }
        
    }
    
    public String getPlayers() {
        int count = 0;
        String s = "[";
        for (Players p: PlayersArray) {
            s += p;
            if (count != 3) {
                s += ",";
            }
        count++;
        }
        s += "]";
        return s;
    }
    
    
}
