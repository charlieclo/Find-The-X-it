/**
 * Class Cell used for Determine each Cell Function including Walls, Borders,
 * Solution to be Passed by Player, and Backtrack to Generate Maze
**/

public class Cell {

	byte[] walls = {1, 1, 1, 1};		// Default Wall 
	byte[] borders = {0, 0, 0, 0};
	byte[] solution = {0, 0, 0, 0};
	byte[] backtrack = {0, 0, 0, 0};
	
	int x, y;
	
	public boolean checkWalls() {
		if (walls[0] == 1 && walls[1] == 1 && walls[2] == 1 && walls[3] == 1) {
			return true;
		}
		
		else {
			return false;
		}
	}

}
