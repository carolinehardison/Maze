import java.util.*;
import java.io.*;

/*
 * Takes in a file and stores it in a local grid
 */
public class Maze {
    private static final int TRIED = 2;
    private static final int PATH = 3;
    
    private int numRow;
    private int numCol;
    private int[][] grid;
    
    public Maze(String filename) throws FileNotFoundException{
	    try{
    	  Scanner scan = new Scanner(new File(filename));
	      numRow = scan.nextInt();
	      numCol = scan.nextInt();
	      grid = new int[numRow][numCol];
	      for(int r = 0; r< numRow; r++){
	        for(int c = 0; c<numCol; c++){
		        grid[r][c]= scan.nextInt();
	        }
	      }
	      scan.close();
	    } catch (FileNotFoundException e){
	    }
    }
    public void tryPosition(Position p) throws NullPointerException{
	    grid[p.getX()][p.getY()] = TRIED;
    }
    
    public int getRows(){
	    return grid.length;
    }
    
    public int getCols(){
	    return grid[0].length;
    }
    
    public void markPath(Position p) throws NullPointerException{
	    grid[p.getX()][p.getY()]= PATH;
    }
    public boolean validPosition(int row, int col){
	    boolean spot = false;
	    if (row>= 0 && row< grid.length && col>= 0 && col < grid[0].length){
	      if (grid[row][col] == 1){
		      spot = true;
	      }
	    }
	    return spot;
    }
    
    @Override
    public String toString(){
        String mazer = "<html>";
        int rc = 0;
        int cc = 0;
        while (rc<numRow){
            cc = 0;
            while(cc<numCol){
                mazer += grid[rc][cc];
                if(cc != numCol -1){
                    mazer += (" ");
                }else {
                    mazer+= ("<br/>");
                }
                cc++;
            }
            rc++;
        }
        mazer+="</html>";
        return mazer;
    }
}
