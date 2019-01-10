/**
 * Class Maze used to Initialize All Cells and also Generate Path of Maze,
 * which the Path will be Randomized using java.util.Random and
 * also each Cell's Criteria will be Made here if this class was Called
**/

import java.util.ArrayList;
import java.util.Stack;

import java.util.Random;

public class Maze {
	
	// Creating Variable that Contain Size of Maze and Arrat of Cells
	int sizeX;
	int sizeY;
	Cell[][] cells;
	
	// Class Constructor Function Maze with Parameter of Maze Width and Maze Height
	public Maze(int width, int height) {
		sizeX = width;
		sizeY = height;
		cells = new Cell[sizeX][sizeY];
		initializeCells();
		generateMaze();
	}
	
	// Function for Initializing each Cell
	public void initializeCells() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				cells[i][j] = new Cell();
				cells[i][j].x = i;
				cells[i][j].y = j;
				
				//Up Border
				if (i == 0) {
					cells[i][j].borders[0] = 1;
				}
				
				//Right Border
				else if (j == sizeY - 1) {
					cells[i][j].borders[1] = 1;
				}
				
				//Bottom Border
				else if (i == sizeX - 1) {
					cells[i][j].borders[2] = 1;
				}
				
				//Left Border
				else if (j == 0) {
					cells[i][j].borders[3] = 1;
				}
			}
		}
	}
	
	// Function for Generating Maze Algorithm
	public void generateMaze() {
		Random rand = new Random();
		
		int x = rand.nextInt(sizeX);
		int y = rand.nextInt(sizeY);
		
		Stack<Cell> cellStack = new Stack<Cell>();
		int totalCells = sizeX * sizeY;
		int visitedCells = 1;
		Cell currentCell = cells[x][y];
		
		ArrayList<Edge> neighbourCellList = new ArrayList<Edge>();
		
		Edge tempEdge = new Edge();
		
		// Repetition Loop while Total of Visited Cells is less than Total Cells of Maze
		while(visitedCells < totalCells) {
			neighbourCellList.clear();
			
			// Check Up Wall
			tempEdge = new Edge();
			if (y - 1 >= 0 && cells[x][y-1].checkWalls() == true) {
				tempEdge.setX1(x);
				tempEdge.setY1(y);
				tempEdge.setX2(x);
				tempEdge.setY2(y - 1);
				tempEdge.setWall1(0);
				tempEdge.setWall2(2);
				neighbourCellList.add(tempEdge);	
			}
			
			// Check Right Wall
			tempEdge = new Edge();
			if (x + 1 < sizeX && cells[x+1][y].checkWalls() == true) {
				tempEdge.setX1(x);
				tempEdge.setY1(y);
				tempEdge.setX2(x + 1);
				tempEdge.setY2(y);
				tempEdge.setWall1(1);
				tempEdge.setWall2(3);
				neighbourCellList.add(tempEdge);	
			}
			
			// Check Bottom Wall
			tempEdge = new Edge();
			if (y + 1 < sizeY && cells[x][y+1].checkWalls() == true) {
				tempEdge.setX1(x);
				tempEdge.setY1(y);
				tempEdge.setX2(x);
				tempEdge.setY2(y + 1);
				tempEdge.setWall1(2);
				tempEdge.setWall2(0);
				neighbourCellList.add(tempEdge);	
			}
			
			// Check Left Wall
			tempEdge = new Edge();
			if (x - 1 >= 0 && cells[x-1][y].checkWalls() == true) {
				tempEdge.setX1(x);
				tempEdge.setY1(y);
				tempEdge.setX2(x - 1);
				tempEdge.setY2(y);
				tempEdge.setWall1(3);
				tempEdge.setWall2(1);
				neighbourCellList.add(tempEdge);	
			}
			
			// When Found an Unvisited Neighbour Cell
			if (neighbourCellList.size() >= 1) {
				int randomCell = rand.nextInt(neighbourCellList.size());
				tempEdge = neighbourCellList.get(randomCell);
				
				//Used to Knock Down Walls Between Cells
				cells[tempEdge.getX1()][tempEdge.getY1()].walls[tempEdge.getWall1()] = 0;
				cells[tempEdge.getX2()][tempEdge.getY2()].walls[tempEdge.getWall2()] = 0;
			
				//Push Current Cell to the Stack so can be Revisited
				cellStack.push(currentCell);
				
				currentCell = cells[tempEdge.getX2()][tempEdge.getY2()];
				
				x = currentCell.x;
				y = currentCell.y;
				
				visitedCells++;
			}
			
			else {
				currentCell = cellStack.pop();
				x = currentCell.x;
				y = currentCell.y;
			}
		}
		
		
	}
	
}
