import java.io.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/GameServlet","/UpdateGame"})
public class GameServlet extends HttpServlet {
    private GameState Game;
    private static int playerCount = 0;
    
    @Override
    public void init() throws ServletException {
        //initialize the game
        Game = new GameState();
    }


    @Override
    protected void doGet(final HttpServletRequest request,final HttpServletResponse response)
            throws ServletException, IOException {

     response.setContentType("text/event-stream;charset=UTF-8");

        try (final PrintWriter out = response.getWriter()) {
            Object attribute = request.getSession().getAttribute("player"); //get player id
            if (attribute == null) {
                if (playerCount <= 3) {
                    int id = playerCount+1;
                    request.getSession().setAttribute("player",id); //set player id for each session
                    playerCount++;
                }
            }

            while (!Thread.interrupted()) {
                synchronized (this) {
                 //create json string containing dots,players and winner    
                 String str = "{ \"DOTS\": " + Game.grid.getDots() + ", "
                            + "\"PLAYERS\": " + Game.getPlayers() + ", "
                            + "\"WINNER\": " + Game.winner() + " }";
                    out.println("data: " + str);
                    out.println();
                    out.flush();
                    wait();
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }            
            
    @Override
    protected void doPost(final HttpServletRequest request,final HttpServletResponse response)
            throws ServletException, IOException {

        synchronized (this) {
            Object obj = request.getSession().getAttribute("player");
            String message = request.getParameter("keypress"); //get keyboard events
            int id = (int) obj;
            Game.updateScore(id, Integer.parseInt(message)); //update the score and  players
            Game.collide(id); //check for collisions
            
            notifyAll();
            Logger.getGlobal().log(Level.INFO, "Received " + message);
        }
        
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
