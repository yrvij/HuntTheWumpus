import java.util.*;
public class Maze extends Setup {
    private static int x = 0;
    private static int y = 0;
    private static Scanner sc = new Scanner(System.in); 
    private static Random rand = new Random();
    
    public int getNumWumpi() {
        placeWumpi();
        return wumpi_loc.size();
    }
    
    public static boolean onObstacle(int x, int y) {
        ArrayList<Integer> coordinate = new ArrayList<Integer>();
        coordinate.add(x);
        coordinate.add(y);
        return (wumpi_loc.contains(coordinate) || pits_loc.contains(coordinate));
    } 
    
    public static boolean onBat(int x, int y) {
        ArrayList<Integer> coordinate = new ArrayList<Integer>();
        coordinate.add(x);
        coordinate.add(y);
        return bats_loc.contains(coordinate);
    }
    
    public static String checkBorders(int x, int y) {
        ArrayList<Integer> coordinate = new ArrayList<Integer>();
        coordinate.add(x);
        coordinate.add(y);
        if (wumpi_loc.contains(coordinate)) {
            return "Wumpi";
        } else if (pits_loc.contains(coordinate)) {
            return "Pit";
        } else {
            return "None";
        }
    }
    
    public static String checkObstacles(int x, int y) {
        String response = "";
        if (checkBorders(x-1,y).equals("Wumpi") || 
            checkBorders(x,y-1).equals("Wumpi") ||
            checkBorders(x,y+1).equals("Wumpi") ||
            checkBorders(x+1,y).equals("Wumpi") ) {
                response += "Stench";
        } 
        if (checkBorders(x-1,y).equals("Pit") || 
            checkBorders(x,y-1).equals("Pit") ||
            checkBorders(x,y+1).equals("Pit") ||
            checkBorders(x+1,y).equals("Pit")) {
                response += "\nBreeze";
        } 
        return response;
    }
 
    public static String move(String action) {
        if (action.charAt(0) == 'm') {
            maze[x][y] = "-";
            if (action.charAt(2) == 'l') {
                maze[x][y-1] = "#";
                y--;
                //System.out.println(checkObstacles(x,y));
                if (onObstacle(x,y)) {
                    return "STOP"; 
                }
                if (onBat(x,y)) {
                    maze[x][y] = "-";
                    x = rand.nextInt(6) + 1;
                    y = rand.nextInt(14) + 1;
                    maze[x][y] = "#";
                    return "YOU HAVE BEEN WHISKED OFF TO A RANDOM LOCATION.";
                }
            } else if (action.charAt(2) == 'r') {
                maze[x][y+1] = "#";
                y++;
                //System.out.println(checkObstacles(x,y));
                if (onObstacle(x,y)) {
                    return "STOP"; 
                }
                if (onBat(x,y)) {
                    maze[x][y] = "-";
                    x = rand.nextInt(6) + 1;
                    y = rand.nextInt(14) + 1;
                    maze[x][y] = "#";
                    return "YOU HAVE BEEN WHISKED OFF TO A RANDOM LOCATION.";
                }
            } else if (action.charAt(2) == 'u') {
                maze[x-1][y] = "#";
                x--;
                //System.out.println(checkObstacles(x,y));
                if (onObstacle(x,y)) {
                    return "STOP"; 
                }
                if (onBat(x,y)) {
                    maze[x][y] = "-";
                    x = rand.nextInt(6) + 1;
                    y = rand.nextInt(14) + 1;
                    maze[x][y] = "#";
                    return "YOU HAVE BEEN WHISKED OFF TO A RANDOM LOCATION.";
                }
            } else {
                maze[x+1][y] = "#";
                x++;
                //System.out.println(checkObstacles(x,y));
                if (onObstacle(x,y)) {
                    return "STOP"; 
                } 
                if (onBat(x,y)) {
                    maze[x][y] = "-";
                    x = rand.nextInt(6) + 1;
                    y = rand.nextInt(14) + 1;
                    maze[x][y] = "#";
                    return "YOU HAVE BEEN WHISKED OFF TO A RANDOM LOCATION.";
                }
            }
        } else {
            return shoot(action.charAt(2));
            /*wumpusReact(x-1,y);
            wumpusReact(x,y+1);
            wumpusReact(x+1,y);
            wumpusReact(x,y-1);*/
        }  
        return checkObstacles(x,y);
    }
       
    public static String shoot(char direction) {
        ArrayList<Integer> coordinate = new ArrayList<Integer>();
        if (direction == 'u') {
            coordinate.add(x-1);
            coordinate.add(y);
            if (wumpi_loc.contains(coordinate)) {
                //numWumpi--;
                wumpi_loc.remove(coordinate);
                maze[x-1][y] = "-";
                coordinate.remove(0);
                coordinate.remove(0);
                return "One wumpus killed!";
            } else {
                return "You missed!!";
            }
        } else if (direction == 'r') {
            coordinate.add(x);
            coordinate.add(y+1);
            if (wumpi_loc.contains(coordinate)) {
                //numWumpi--;
                wumpi_loc.remove(coordinate);
                maze[x][y+1] = "-";
                coordinate.remove(0);
                coordinate.remove(0);
                return "One wumpus killed!";
            } else {
                return "You missed!!";
            }
        } else if (direction == 'd') {
            coordinate.add(x+1);
            coordinate.add(y);
            if (wumpi_loc.contains(coordinate)) {
                //numWumpi--;
                wumpi_loc.remove(coordinate);
                maze[x+1][y] = "-";
                coordinate.remove(0);
                coordinate.remove(0);
                return "One wumpus killed!";
            } else {
                return "You missed!!";
            }
        } else {
            coordinate.add(x);
            coordinate.add(y-1);
            if (wumpi_loc.contains(coordinate)) {
                //numWumpi--;
                wumpi_loc.remove(coordinate);
                maze[x][y-1] = "-";
                coordinate.remove(0);
                coordinate.remove(0);
                return "One wumpus killed!";
            } else {
                return "You missed!!";
            }
        }
    }
   
    public static void wumpusReact(int x, int y) {
        ArrayList<Integer> coordinate = new ArrayList<Integer>();
        coordinate.add(x);
        coordinate.add(y);
        if (wumpi_loc.contains(coordinate)) {
            int index = wumpi_loc.indexOf(coordinate);
            int direction = rand.nextInt(4) + 1;
            if (direction == 1) {
                ArrayList<Integer> new_coord = new ArrayList<Integer>();
                new_coord.add(wumpi_loc.get(index).get(0)-1,wumpi_loc.get(index).get(1));
                maze[wumpi_loc.get(index).get(0)][wumpi_loc.get(index).get(1)] = "-";
                maze[wumpi_loc.get(index).get(0)-1][wumpi_loc.get(index).get(1)] = "X";
                wumpi_loc.remove(index);
                wumpi_loc.add(new_coord);
            } else if (direction == 2) {
                ArrayList<Integer> new_coord = new ArrayList<Integer>();
                new_coord.add(wumpi_loc.get(index).get(0),wumpi_loc.get(index).get(1)+1);
                maze[wumpi_loc.get(index).get(0)][wumpi_loc.get(index).get(1)] = "-";
                maze[wumpi_loc.get(index).get(0)][wumpi_loc.get(index).get(1)+1] = "X";
                wumpi_loc.remove(index);
                wumpi_loc.add(new_coord);
            } else if (direction == 3) {
                ArrayList<Integer> new_coord = new ArrayList<Integer>();
                new_coord.add(wumpi_loc.get(index).get(0)+1,wumpi_loc.get(index).get(1));
                maze[wumpi_loc.get(index).get(0)][wumpi_loc.get(index).get(1)] = "-";
                maze[wumpi_loc.get(index).get(0)+1][wumpi_loc.get(index).get(1)] = "X";
                wumpi_loc.remove(index);
                wumpi_loc.add(new_coord);
            } else {
                ArrayList<Integer> new_coord = new ArrayList<Integer>();
                new_coord.add(wumpi_loc.get(index).get(0),wumpi_loc.get(index).get(1)-1);
                maze[wumpi_loc.get(index).get(0)][wumpi_loc.get(index).get(1)] = "-";
                maze[wumpi_loc.get(index).get(0)][wumpi_loc.get(index).get(1)-1] = "X";
                wumpi_loc.remove(index);
                wumpi_loc.add(new_coord);
            }
        } 
    }
}