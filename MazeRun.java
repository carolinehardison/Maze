import java.io.IOException;
public class MazeRun{
    public static void main(String[] args) throws IOException, NullPointerException{
	    Maze maze = new Maze("Maze.txt");
    	MazeRunner mR = new MazeRunner(maze);
	    mR.traverse();
	    Maze mazeDone = mR.getMaze();
	    System.out.println(mazeDone.toString());
    }
}
