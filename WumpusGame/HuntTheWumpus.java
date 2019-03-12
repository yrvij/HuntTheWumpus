import java.util.*;
import java.time.*;
public class HuntTheWumpus extends Maze {

    public static void clrScr() {  
        System.out.print('\u000C'); 
    }
    
    public static void main(String[] args) {
       Random rand = new Random();
       HuntTheWumpus client = new HuntTheWumpus();
       Scanner sc = new Scanner(System.in);
       client.initializeMaze();
       client.printMaze();  
       while (true) {
           String action = sc.nextLine();
           String response = client.move(action);
           if (response.equals("STOP")) {
               System.out.println("GAME OVER!");
               try {
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               clrScr();
               break;
           } else if (response.charAt(0) == 'Y') {
               System.out.println(response);
               client.printMaze();
           } else {
               client.printMaze();
           }
       }
    }
}