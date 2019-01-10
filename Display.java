/**
 * Class Display used for Displaying Maze Panel if It was Called.
 * This Classed will Make : Player, Coin, Trap, and Exit
 * Available Key Listener to Move the Player
 * 
**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Path2D;

import java.util.Random;

public class Display extends JPanel implements KeyListener {
	
	Random rand = new Random();
	
	Maze firstMaze;
	int offsetX = 20;
	int offsetY = 20;
	int cellSize = 35;
	
	// Creating All Variables that Needed for Displaying Maze
	int pointX, pointY, oldX, oldY;
	int[] checkCoin = new int[5];
	int[] coinX = new int[5];
	int[] coinY = new int[5];
	int[] checkTrap = new int[3];
	int[] trapX = new int[3];
	int[] trapY = new int[3];
	int indexCoinNearWall, indexTrapNearWall = -1;
	boolean erase;
	int winning;
	int pause = 0;
	
	// Function for Generating Coin and Trap in the Maze
	public void generateCoinTrap() {
		int coin = 0;
		int trap = 0;
		
		// Repetition Loop while Total Coin is not 5
		while(coin != 5) {
			int randCoinX = rand.nextInt((18 - 1) + 1) + 1;
			coinX[coin] = randCoinX * 35 + 25;
			int randCoinY = rand.nextInt((18 - 1) + 1) + 1;
			coinY[coin] = randCoinY * 35 + 25;
			checkCoin[coin] = 1;
			coin++;
		}
		
		// Repetition Loop while Total Trap is not 3
		while(trap != 3) {
			int randTrapX = rand.nextInt((18 - 1) + 1) + 1;
			trapX[trap] = randTrapX * 35 + 25;
			int randTrapY = rand.nextInt((18 - 1) + 1) + 1;
			trapY[trap] = randTrapY * 35 + 25;
			checkTrap[trap] = 1;
			trap++;
		}
	}
	
	// Class Constructor Function Display with Parameter of New Object of Class Maze and Cell Size of Maze
	public Display(Maze maze, int newCellSize) {
		firstMaze = maze;
		cellSize = newCellSize;
		pointX = offsetX + cellSize / 2;
		pointY = offsetY + cellSize / 2;
		oldX = pointX;
		oldY = pointY;
		generateCoinTrap();
		addKeyListener(this);	
	}
	
	// Function for Drawing Maze
	public void drawMaze(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Path2D mazePath = new Path2D.Double();
		
		// Getting Size of Width and Height of Display Panel
		int width = getSize().width - getInsets().left - getInsets().right;
		int height = getSize().height - getInsets().top - getInsets().bottom;
		
		// Set Background of Rectangle Graphic as Maze Background with Width and Height of Display Panel  
		g2d.setBackground(Color.WHITE);
		g2d.clearRect(0, 0, width, height);
		
		// Calling Variable of Position X and Position Y
		int posX, posY;
		
		// Initialize Winning Condition with 0 as Default everytime New Maze was Created
		winning = 0;
		
		// Drawing Maze with Walls of each Cell
		for (int i = 0; i < firstMaze.sizeX; i++) {
			posX = i * cellSize + offsetX;
			for (int j = 0; j < firstMaze.sizeY; j++) {
				posY = j * cellSize + offsetY;
				
				// Condition if Cell have Up Wall
				if (firstMaze.cells[i][j].walls[0] == 1) {
					mazePath.moveTo(posX, posY);
					mazePath.lineTo(posX + cellSize, posY);
					g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
					g2d.drawLine(posX, posY, posX + cellSize, posY);
				}
				
				// Condition if Cell have Right Wall
				if (firstMaze.cells[i][j].walls[1] == 1) {
					mazePath.moveTo(posX + cellSize, posY);
					mazePath.lineTo(posX + cellSize, posY + cellSize);
					g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
					g2d.drawLine(posX + cellSize, posY, posX + cellSize, posY + cellSize);
				}
				
				// Condition if Cell have Bottom Wall
				if (firstMaze.cells[i][j].walls[2] == 1) {
					mazePath.moveTo(posX, posY + cellSize);
					mazePath.lineTo(posX + cellSize, posY + cellSize);
					g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
					g2d.drawLine(posX, posY + cellSize, posX + cellSize, posY + cellSize);
				}
				
				// Condition if Cell have Left Wall
				if (firstMaze.cells[i][j].walls[3] == 1) {
					mazePath.moveTo(posX, posY);
					mazePath.lineTo(posX, posY + cellSize);
					g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
					g2d.drawLine(posX, posY, posX, posY + cellSize);
				}
			}
		}
		
		// Setting Player Position with its Condition
		posX = (oldX - offsetX - cellSize / 2) / cellSize;
		posY = (oldY - offsetY - cellSize / 2) / cellSize;
		
		// Condition for Player Moving Up Through The Wall
		if (posY >= 0 && posY < firstMaze.sizeY && oldY > pointY && firstMaze.cells[posX][posY].walls[0] == 1) {
			// Player will be Back to Old Position
			pointX = oldX;
			pointY = oldY;
			
			// Checking if Coin was Stepped while Player was Moving thorugh Wall
			if (indexCoinNearWall != -1) {
				checkCoin[indexCoinNearWall] = 1;
				indexCoinNearWall = -1;
			}

			// Checking if Trap was Stepped while Player was Moving thorugh Wall
			if (indexTrapNearWall != -1) {
				checkTrap[indexTrapNearWall] = 1;
				indexTrapNearWall = -1;
			}
		}
		
		// Condition for Player Moving Right Through The Wall
		else if (posX >= 0 && posX < firstMaze.sizeX && oldX < pointX && firstMaze.cells[posX][posY].walls[1] == 1) {
			pointX = oldX;
			pointY = oldY;
			
			// Checking if Coin was Stepped while Player was Moving thorugh Wall
			if (indexCoinNearWall != -1) {
				checkCoin[indexCoinNearWall] = 1;
				indexCoinNearWall = -1;
			}
			
			// Checking if Trap was Stepped while Player was Moving thorugh Wall
			if (indexTrapNearWall != -1) {
				checkTrap[indexTrapNearWall] = 1;
				indexTrapNearWall = -1;
			}
		}
		
		//Condition for Player Moving Down Through The Wall
		else if (posY >= 0 && posY < firstMaze.sizeY && oldY < pointY && firstMaze.cells[posX][posY].walls[2] == 1) {
			pointX = oldX;
			pointY = oldY;
			
			// Checking if Coin was Stepped while Player was Moving thorugh Wall
			if (indexCoinNearWall != -1) {
				checkCoin[indexCoinNearWall] = 1;
				indexCoinNearWall = -1;
			}
			
			// Checking if Trap was Stepped while Player was Moving thorugh Wall
			if (indexTrapNearWall != -1) {
				checkTrap[indexTrapNearWall] = 1;
				indexTrapNearWall = -1;
			}
		}
		
		//Condition for Player Moving Left Through The Wall
		else if (posX >= 0 && posX < firstMaze.sizeX && oldX > pointX && firstMaze.cells[posX][posY].walls[3] == 1) {
			pointX = oldX;
			pointY = oldY;
			
			// Checking if Coin was Stepped while Player was Moving thorugh Wall
			if (indexCoinNearWall != -1) {
				checkCoin[indexCoinNearWall] = 1;
				indexCoinNearWall = -1;
			}
			
			// Checking if Trap was Stepped while Player was Moving thorugh Wall
			if (indexTrapNearWall != -1) {
				checkTrap[indexTrapNearWall] = 1;
				indexTrapNearWall = -1;
			}
		}
		
		//Winning Condition
		if ((posX == firstMaze.sizeX - 1 && posY == firstMaze.sizeY - 2) || (posX == firstMaze.sizeX - 2 && posY == firstMaze.sizeY - 1) || (posX == firstMaze.sizeX - 1 && posY == firstMaze.sizeY - 1)) {
			winning = 1;
		}
		
		//Exit Door
		g.setColor(Color.decode("#00C3E5"));
		g.fillRect(690, 690, 25, 25);
		
		//Coin
		for (int i = 0; i < coinX.length; i++) {
			if (checkCoin[i] == 1) {
				g.setColor(Color.decode("#FBC02D"));
				g.fillRect(coinX[i], coinY[i], 25, 25);
			}
			
			else {
				g.setColor(Color.WHITE);
				g.fillRect(coinX[i], coinY[i], 25, 25);
			}
		}
	
		//Trap
		for (int i = 0; i < trapX.length; i++) {
			if (checkTrap[i] == 1) {
				g.setColor(Color.decode("#ED020A"));
				g.fillRect(trapX[i], trapY[i], 25, 25);
			}
			
			else {
				g.setColor(Color.WHITE);
				g.fillRect(trapX[i], trapY[i], 25, 25);
			}
		}
		
		//Player
		g.setColor(Color.decode("#07F723"));
		g.fillRect(pointX - 12, pointY - 12, 25, 25);
				
	}
	
	// Paint Function that will be Run when repaint() is Called
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawMaze(g);
	}
	
	// Key Listener for Display Panel
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		oldX = pointX;
		oldY = pointY;
		
		// Condition for Player Move Up
		if (key == e.VK_UP || key == e.VK_W) {
			pointY = pointY - cellSize;
			indexCoinNearWall = -1;
			indexTrapNearWall = -1;
			
			// Condition if Player Position after Moving is Passing Through Outer Wall
			if (pointY < 0) {
				pointY = 0;
			}
			
			// Checking if Coin has been Stepped by Player or Not
			for (int i = 0; i < checkCoin.length; i++) {
				if (coinX[i] == pointX - 12 && coinY[i] == pointY - 12 && checkCoin[i] == 1) {
					checkCoin[i] = 0;
					indexCoinNearWall = i;
				}
			}
			
			// Checking if Trap has been Stepped by Player or Not
			for (int i = 0; i < checkTrap.length; i++) {
				if (trapX[i] == pointX - 12 && trapY[i] == pointY - 12 && checkTrap[i] == 1) {
					checkTrap[i] = 0;
					indexTrapNearWall = i;
				}
			}
			
			// Calling paint() Function to Re-Draw the Maze each Player has been Moved
			repaint();
		}
		
		// Condition for Player Move Right
		else if (key == e.VK_RIGHT || key == e.VK_D) {
			pointX = pointX + cellSize;
			indexCoinNearWall = -1;
			indexTrapNearWall = -1;
			
			// Condition if Player Position after Moving is Passing Through Outer Wall
			if (pointX > getWidth()) {
				pointX = getWidth();
			}
			
			// Checking if Coin has been Stepped by Player or Not
			for (int i = 0; i < checkCoin.length; i++) {
				if (coinX[i] == pointX - 12 && coinY[i] == pointY - 12 && checkCoin[i] == 1) {
					checkCoin[i] = 0;
					indexCoinNearWall = i;
				}
			}
			
			// Checking if Trap has been Stepped by Player or Not
			for (int i = 0; i < checkTrap.length; i++) {
				if (trapX[i] == pointX - 12 && trapY[i] == pointY - 12 && checkTrap[i] == 1) {
					checkTrap[i] = 0;
					indexTrapNearWall = i;
				}
			}
			
			// Calling paint() Function to Re-Draw the Maze each Player has been Moved
			repaint();
		}
		
		// Condition for Player Move Bottom
		else if (key == e.VK_DOWN || key == e.VK_S) {
			pointY = pointY + cellSize;
			indexCoinNearWall = -1;
			indexTrapNearWall = -1;
			
			// Condition if Player Position after Moving is Passing Through Outer Wall
			if (pointY > getHeight()) {
				pointY = getHeight();
			}
			
			// Checking if Coin has been Stepped by Player or Not
			for (int i = 0; i < checkCoin.length; i++) {
				if (coinX[i] == pointX - 12 && coinY[i] == pointY - 12 && checkCoin[i] == 1) {
					checkCoin[i] = 0;
					indexCoinNearWall = i;
				}
			}
			
			// Checking if Trap has been Stepped by Player or Not
			for (int i = 0; i < checkTrap.length; i++) {
				if (trapX[i] == pointX - 12 && trapY[i] == pointY - 12 && checkTrap[i] == 1) {
					checkTrap[i] = 0;
					indexTrapNearWall = i;
				}
			}
			
			// Calling paint() Function to Re-Draw the Maze each Player has been Moved
			repaint();
		}
		
		// Condition for Player Move Left
		else if (key == e.VK_LEFT || key == e.VK_A) {
			pointX = pointX - cellSize;
			indexCoinNearWall = -1;
			indexTrapNearWall = -1;
			
			// Condition if Player Position after Moving is Passing Through Outer Wall
			if (pointX < 0) {
				pointX = 0;
			}
			
			// Checking if Coin has been Stepped by Player or Not
			for (int i = 0; i < checkCoin.length; i++) {
				if (coinX[i] == pointX - 12 && coinY[i] == pointY - 12 && checkCoin[i] == 1) {
					checkCoin[i] = 0;
					indexCoinNearWall = i;
				}
			}
			
			// Checking if Trap has been Stepped by Player or Not
			for (int i = 0; i < checkTrap.length; i++) {
				if (trapX[i] == pointX - 12 && trapY[i] == pointY - 12 && checkTrap[i] == 1) {
					checkTrap[i] = 0;
					indexTrapNearWall = i;
				}
			}
			
			// Calling paint() Function to Re-Draw the Maze each Player has been Moved
			repaint();
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
