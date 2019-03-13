import java.io.*;
import java.util.*;

public class Setup {
    public static String[][] maze = new String[10][17];
    public static Random rand = new Random();
    public static ArrayList<ArrayList<Integer>> wumpi_loc = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<ArrayList<Integer>> pits_loc = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<ArrayList<Integer>> bats_loc = new ArrayList<ArrayList<Integer>>();
   
    public void placeWumpi() {
        int numWumpi = rand.nextInt(21) + 10; 
        for (int i = 0; i < numWumpi; i++) {
            while (true) {
                ArrayList<Integer> wumpi_coord = new ArrayList<Integer>();
                int x = rand.nextInt(6) + 1; // 1-8
                int y = rand.nextInt(14) + 1; // 1-16
                wumpi_coord.add(x);
                wumpi_coord.add(y);
                if (wumpi_loc.contains(wumpi_coord)) {
                    continue;
                } else {
                    wumpi_loc.add(wumpi_coord);
                    maze[x][y] = "X";
                    break;
                }
            }
        }
        //System.out.println(wumpi_loc);
    }
    
    public void placePits() {
        int numWumpi = rand.nextInt(11) + 10; 
        for (int i = 0; i < numWumpi; i++) {
            while (true) {
                ArrayList<Integer> pits_coord = new ArrayList<Integer>();
                int x = rand.nextInt(6) + 1;
                int y = rand.nextInt(14) + 1;
                pits_coord.add(x);
                pits_coord.add(y);
                if (wumpi_loc.contains(pits_coord) || pits_loc.contains(pits_coord)) {
                    continue;
                } else {
                    pits_loc.add(pits_coord);
                    maze[x][y] = "O";
                    break;
                }
            }
        }
        //System.out.println(pits_loc);
        //System.out.println(pits_loc.size());
    }
    
    public void placeBats() {
        int numBats = rand.nextInt(7) + 1;
        for (int i = 0; i < numBats; i++) {
            while (true) {
                ArrayList<Integer> bats_coord = new ArrayList<Integer>();
                int x = rand.nextInt(6) + 1;
                int y = rand.nextInt(14) + 1;
                bats_coord.add(x);
                bats_coord.add(y);
                if (wumpi_loc.contains(bats_coord) || pits_loc.contains(bats_coord)
                || bats_loc.contains(bats_coord)) {
                    continue;
                } else {
                    bats_loc.add(bats_coord);
                    maze[x][y] = "B";
                    break;
                }
            }
        }
    }
            
    public void initializeMaze() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 15; j++) {
                maze[i][j] = "-";
            }
        }
        maze[0][0] = "#";
        for (ArrayList<Integer> a : wumpi_loc) {
            maze[a.get(0)][a.get(1)] = "X";
        }
        placePits();
        placeBats();
    }
    
    public static void printMaze() {
       for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
       }
    }
}