import java.util.*;
public class MazeRunner {
    private Maze maze;
    private Deque<Position> runner;
    private Deque<Position> temp;

    public MazeRunner(Maze maze){
	    this.maze = maze;
	    runner = new LinkedList<Position>();
	    temp = new LinkedList<Position>();
    }
   public boolean traverse(){
		boolean done = false;
		Position pos = new Position(0, 0);
		runner.push(pos);
		while(done != true ){
			Position cur = runner.pop();
			temp.push(cur);
			maze.markPath(cur);
			if (cur.getX() == maze.getRows() && cur.getY() == maze.getCols()){
				done = true;
			} else {
				if(maze.validPosition(cur.getX() - 1, cur.getY())){
					runner.push(new Position(cur.getX() - 1, cur.getY()));
				}
				if(maze.validPosition(cur.getX() + 1, cur.getY())){
					runner.push(new Position(cur.getX() + 1, cur.getY()));
				}
				if(maze.validPosition(cur.getX(), cur.getY() - 1)){
					runner.push(new Position(cur.getX(), cur.getY() - 1));
				}
				if(maze.validPosition(cur.getX(), cur.getY() + 1)){
					runner.push(new Position(cur.getX(), cur.getY() + 1));
				} else {
					Position tryfail = new Position();
					while (!temp.isEmpty()){
						tryfail = temp.pop();
						maze.tryPosition(tryfail);
					}
					maze.markPath(tryfail);
				}
			}
		}
		return true;
	}
  
  public Maze getMaze(){
	  return maze;
  }
}
