import java.util.*;
public class MazeRunner {
    private Maze maze;
    private Deque<Position> runner;
    private Deque<Position> temp;

    public MazeRunner(Maze maze){
	    this.maze = maze;
	    Deque<Position> runner = new LinkedList<Position>();
      Deque<Position> temp = new LinkedList<Position>();
	    this.runner = runner;
	    this.temp = temp;
    }
    public boolean traverse(){
	    boolean done = false;
	    Position nil = new Position(-1,-1);
	    temp.push(nil);
	    Position pos = new Position(0, 0);
	    runner.push(pos);
	    while(done != true ){
	      System.out.println(maze.toString());
	      if(runner.isEmpty()){
		      return false;
	      }
	      Position cur = runner.pop();
	      temp.push(cur);
	      maze.markPath(cur);
	      if (cur.getX() == maze.getRows() - 1 && cur.getY() == maze.getCols() - 1)
		      return true;
	      else {
		      int count = 0;
		      if(maze.validPosition(cur.getX() - 1, cur.getY())){
		        Position left = new Position(cur.getX() - 1, cur.getY());
		        runner.push(left);
		        count++;
		      }
		      if(maze.validPosition(cur.getX() + 1, cur.getY())){
		        Position right = new Position(cur.getX() + 1, cur.getY());
		        runner.push(right);
		        count++;
		      }
		      if(maze.validPosition(cur.getX(), cur.getY() - 1)){
		        Position down = new Position(cur.getX(), cur.getY() - 1);
		        runner.push(down);
		        count++;
		      }
		      if(maze.validPosition(cur.getX(), cur.getY() + 1)){
		        Position up = new Position(cur.getX(), cur.getY() + 1);
		        runner.push(up);
		        count++;
		      }
		      if(count>1){
		        temp.push(nil);
		      }
		      if(count == 0){
		        Position failedPath = temp.pop();
		        while (!failedPath.same(nil)){
			        maze.tryPosition(failedPath);
			        if(temp.isEmpty()){
			        return false;
			      }
			      failedPath = temp.pop();
		      }
		    }
	    }
	  }
	  return true;
  }
  
  public Maze getMaze(){
	  return maze;
  }
}
