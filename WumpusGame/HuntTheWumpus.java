import java.util.*;
import java.time.*;
public class HuntTheWumpus extends Maze {
    private static int numWumpi;
    private static boolean isAlive;
    private static int numGrenades;
    
    public static void clrScr() {  
        System.out.print('\u000C'); 
    }
    
    public void pause(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
       Random rand = new Random();
       Scanner sc = new Scanner(System.in);
       HuntTheWumpus client = new HuntTheWumpus();      
       numWumpi = client.getNumWumpi();
       numGrenades = numWumpi * 2;
       isAlive = true;
       client.initializeMaze();
       while (isAlive && numWumpi > 0) {
           System.out.println("There are " + numWumpi + " wumpi left.");
           System.out.println("You currently have " + numGrenades + " grenades left.");
           System.out.println();
           System.out.println("What would you like to do? move or throw");
           System.out.println("Enter 'm' or 't' with a direction (u,l,d,r)");
           System.out.println();
           client.printMaze();
           System.out.println();
           
           String action = sc.nextLine();
           String response = client.move(action);
           if (response.equals("STOP")) {
               isAlive = false;
               System.out.println("GAME OVER!");
               client.pause(2000);
               clrScr();
           } else if (response.length() > 4 && response.charAt(4) == 'H') { 
               System.out.println(response);
               client.pause(750);
               clrScr();
               //client.printMaze();
           } else if (response.length() > 0 && response.charAt(0) == 'O') {
               System.out.println(response);
               numWumpi--;
               numGrenades--;
               client.pause(500);
               clrScr();
               //client.printMaze();
           } else if (response.length() > 0 && response.charAt(0) == 'Y') {
               System.out.println(response);
               numGrenades--;
               client.pause(500);
               clrScr();
           } else if (response.length() > 0) {
               System.out.println("WARNING: " + response);
               System.out.println();
           } else {
               clrScr();
           }
               
           
           if (numGrenades == 0) {
               System.out.println("You are out of grenades!! Play safely!");
           }
       }
       if (numWumpi == 0) {
           clrScr();
           System.out.println("CONGRATULATIONS! YOU WON THE GAME!!");
       }
    }
}