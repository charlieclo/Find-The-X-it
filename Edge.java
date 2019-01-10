/**
 * Class Edge used as Temporary Class that Contain Values of
 * each Cell's Criteria such as if that Cell have Wall or Not
**/

public class Edge {

	private int x1, y1;
	private int x2, y2;
	private int wall1, wall2;
	
	// Setter and Getter of Variable to be used in Maze for Generating Maze
	public int getX1() {
		return x1;
	}
	
	public void setX1(int x1) {
		this.x1 = x1;
	}
	
	public int getY1() {
		return y1;
	}
	
	public void setY1(int y1) {
		this.y1 = y1;
	}
	
	public int getX2() {
		return x2;
	}
	
	public void setX2(int x2) {
		this.x2 = x2;
	}
	
	public int getY2() {
		return y2;
	}
	
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public int getWall1() {
		return wall1;
	}
	
	public void setWall1(int wall1) {
		this.wall1 = wall1;
	}
	
	public int getWall2() {
		return wall2;
	}
	
	public void setWall2(int wall2) {
		this.wall2 = wall2;
	}
}
