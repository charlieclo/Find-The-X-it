/**
 * Class Main used for JFrame and could use Key Listener as a main frame for Main Menu.
 * In this Class, Timer was Set and could be Paused with SPace Button
 * Main Frame always use for Creating a New Level
 * Hover This to Show The Exit Button
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.Timer;
import java.util.ArrayList;

public class Main extends JFrame implements KeyListener {
	
	// Initialization for Time, Life, Level
	public static int time = 25;
	public static int life = 3;
	public static int level = 1;
	
	// Boolean for Pause
	boolean canPause = false;
	
	// Initialization for Width, Height, and each Cell size for Maze
	int sizeX = 20;		//sizeX for Width of Maze
	int sizeY = 20;		//sizeY for Height of Maze
	int cellSize = 35;	//cellSize for Cell Size per Square of Maze
	
	// Constructing New Maze
	Maze maze = new Maze(sizeX, sizeY);
	
	// Calling New Object of Timer
	Timer timer = new Timer(1000, null);
	
	// Calling New Object for Display Maze and Components for Main Menu
	Display panelMaze;									// New Object for Displaying Maze
	JPanel panelMenu;									// Menu Panel
	JLabel lblTitle;									// Title Text Label
	JLabel lblTimeText, lblTime;						// Time Text and Time Label
	JLabel lblLifeText, lblLife;						// Life Text and Life Label
	JLabel lblLevelText, lblLevel;						// Level Text and Level Label
	JLabel lblBlueRect, lblYourGoal;					// Blue Rectangle and its Text Label
	JLabel lblGreenRect, lblPlayer;						// Green Rectangle and its Text Label
	JLabel lblYellowRect, lblCoin;						// Yellow Rectangle and its Text Label
	JLabel lblRedRect, lblTrap;							// Red Rectangle and its Text Label
	JLabel lblPressPause;								// Pressing Pause Text Label
	JLabel lblHoverText, lblHoverThis, lblHoverText2;	// Hover This Text Label
	JButton btnExit;									// Exit Button
	
	// Launch the Application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Constructing New Game
					Main game = new Main(time, life, level);
					game.setVisible(true);
				} 
				
				//Error Handler
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the Application
	public Main(int time, int life, int level) {
		// Calling Initialization Function with Default Time, Life, and Level
		initialize(time, life, level);
	}	
	
	// Initialize Components of Main Frame
	private void initialize(int newTime, int newLife, int newLevel) {
		//Initialization for Time, Life, and Level
		time = newTime;
		life = newLife;
		level = newLevel;
		
		//Setting for Main Frame
		setTitle("Find The X-it");
		setResizable(false);
		setBounds(100, 100, 1300, 780);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		
		//Construct Maze Components
		panelMaze = new Display(maze, cellSize);
		
		//Construct All Menu Components for Main Frame
		panelMenu = new JPanel();
		lblTitle = new JLabel("Find The X-it");
		lblTimeText = new JLabel("Time Left :");
		lblTime = new JLabel(Integer.toString(time));
		lblLifeText = new JLabel("Life :");
		lblLife = new JLabel(Integer.toString(life));
		lblLevelText = new JLabel("Level :");
		lblLevel = new JLabel(Integer.toString(level));
		lblBlueRect = new JLabel("");
		lblYourGoal = new JLabel("Your Goal");
		lblGreenRect = new JLabel("");
		lblPlayer = new JLabel("Player");
		lblYellowRect = new JLabel("");
		lblCoin = new JLabel("Coin (Extra Time)");
		lblRedRect = new JLabel("");
		lblTrap = new JLabel("It's a Trap !");
		lblPressPause = new JLabel("Press 'Space' to Pause the Game");
		lblHoverText = new JLabel("Hover");
		lblHoverThis = new JLabel("THIS");
		lblHoverText2 = new JLabel("to Show Exit Button");
		btnExit = new JButton("Exit");
		
		//Construct View Layout for Find the X-it Frame
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelMaze, GroupLayout.PREFERRED_SIZE, 740, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelMenu, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
		);
		
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelMaze, GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
				.addComponent(panelMenu, GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
		);
		
		//Construct View Layout for Maze Panel
		GroupLayout gl_panelMaze = new GroupLayout(panelMaze);
		gl_panelMaze.setHorizontalGroup(
			gl_panelMaze.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelMaze.createSequentialGroup()
					.addContainerGap())
		);
		gl_panelMaze.setVerticalGroup(
			gl_panelMaze.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelMaze.createSequentialGroup()
					.addContainerGap())
		);
		// Add Maze Panel into Frame
		panelMaze.setLayout(gl_panelMaze);
		
		// Construct View Component for Menu Panel
		// Title
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		// Time Text
		lblTimeText.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Time
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Life Text
		lblLifeText.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Life
		lblLife.setForeground(Color.RED);
		lblLife.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Level Text
		lblLevelText.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Level
		lblLevel.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Blue Rectangle
		lblBlueRect.setOpaque(true);
		lblBlueRect.setBackground(Color.decode("#00C3E5"));
		// Your Goal Text
		lblYourGoal.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Green Rectangle
		lblGreenRect.setOpaque(true);
		lblGreenRect.setBackground(Color.decode("#07F723"));
		// Player Text
		lblPlayer.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Yellow Rectangle
		lblYellowRect.setOpaque(true);
		lblYellowRect.setBackground(Color.decode("#FBC02D"));
		// Coin Text
		lblCoin.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Red Rectangle
		lblRedRect.setOpaque(true);
		lblRedRect.setBackground(Color.decode("#ED020A"));
		// Trap Text
		lblTrap.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Press Pause Text
		lblPressPause.setForeground(new Color(148, 0, 211));
		lblPressPause.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Hover Text
		lblHoverText.setForeground(new Color(148, 0, 211));
		lblHoverText.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Hover This Mouse Listener for Showing Exit Button
		lblHoverThis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblHoverThis.setForeground(Color.BLUE);
				btnExit.setVisible(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblHoverThis.setForeground(Color.BLUE);
				btnExit.setVisible(true);
			}
		});
		lblHoverThis.setForeground(new Color(255, 0, 0));
		lblHoverThis.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Hover Text2
		lblHoverText2.setForeground(new Color(148, 0, 211));
		lblHoverText2.setFont(new Font("Tahoma", Font.BOLD, 20));
		// Exit Button Mouse Listener for Exiting Application when Pressed
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExit.setVisible(false);
		
		// Construct View Layout for Menu Panel
		GroupLayout gl_panelMenu = new GroupLayout(panelMenu);
		gl_panelMenu.setHorizontalGroup(
			gl_panelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenu.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(lblHoverText)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblHoverThis, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(lblHoverText2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panelMenu.createSequentialGroup()
										.addComponent(lblGreenRect, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblPlayer, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panelMenu.createSequentialGroup()
										.addComponent(lblBlueRect, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblYourGoal, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panelMenu.createSequentialGroup()
										.addComponent(lblYellowRect, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblCoin, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
									.addGroup(gl_panelMenu.createSequentialGroup()
										.addComponent(lblRedRect, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblTrap, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblPressPause, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelMenu.createSequentialGroup()
									.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTimeText)
										.addComponent(lblLifeText)
										.addComponent(lblLevelText, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLevel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblLife, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))))
							.addGap(70))
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panelMenu.setVerticalGroup(
			gl_panelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMenu.createSequentialGroup()
					.addGap(49)
					.addComponent(lblTitle)
					.addGap(59)
					.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(lblTimeText)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLifeText)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLevelText, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLife, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLevel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addGap(47)
							.addComponent(lblBlueRect, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addGap(59)
							.addComponent(lblYourGoal, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblGreenRect, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addGap(25)
							.addComponent(lblPlayer, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblYellowRect, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addGap(24)
							.addComponent(lblCoin, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblRedRect, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelMenu.createSequentialGroup()
							.addGap(26)
							.addComponent(lblTrap, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(40)
					.addComponent(lblPressPause, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panelMenu.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHoverText, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelMenu.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblHoverText2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblHoverThis, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(btnExit)
					.addContainerGap(87, Short.MAX_VALUE))
		);
		// Add Menu Panel into Frame
		panelMenu.setLayout(gl_panelMenu);
		
		// Set Layout for Main Frame
		getContentPane().setLayout(groupLayout);
		
		// Adding Action Listener for Timer
		timer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Setting Timer Delay Every 1000 ms or 1 second
				timer.setDelay(1000);
				// Time will be Decreased by 1 after Timer Delay
				time = time - 1;
				lblTime.setText(Integer.toString(time));
				System.out.println("Time : " + time);
				System.out.println("Life : " + life);
				System.out.println("Level: " + level);
				
				// Condition of Losing Game
				if (time == 0 || life == 0) {
					timer.stop();
					JOptionPane.showMessageDialog(null, "You Lose !", "Message", 1);
					Main game = new Main(25, 3, 1);
					game.setVisible(true);
					dispose();
				}
				
				// Condition of Winning
				if (panelMaze.winning == 1) {
					panelMaze.winning = 0;
					timer.stop();
					JOptionPane.showMessageDialog(null, "Congratulations, you've beaten the level!", "End Game", JOptionPane.INFORMATION_MESSAGE);
					level = level + 1;		// Level will be Increased by 1
					time = time - 3;		// Time will be Decreased by 3
					
					// Condition of Winning the Game
					if (level == 7) {
						timer.stop();
						JOptionPane.showMessageDialog(null, "You Win !", "Message", 1);
						dispose();			// Main Frame will be Closed
					}
					
					// Condition of Winning the Level
					else {
						life = 3;			//Life will be Initialized by 3
						// Constructing New Game with Different Level
						Main game = new Main(time, life, level);
						game.setVisible(true);
						dispose();			// Main Frame will be Closed
					}
				}
				
				// Checking for All Coins if They have already been Passed or Not
				for (int i = 0; i < panelMaze.checkCoin.length; i++) {
					if (panelMaze.checkCoin[i] == 0) {
						time = time + 5;	// Time will be Increased by 5
						lblTime.setText(Integer.toString(time));
						panelMaze.checkCoin[i] = 2;
					}
				}
				
				// Checking for All Traps if They have already been Passed or Not
				for (int i = 0; i < panelMaze.checkTrap.length; i++) {
					if (panelMaze.checkTrap[i] == 0) {
						life = life - 1;	// Life will be Decreased by 1
						lblLife.setText(Integer.toString(life));
						panelMaze.checkTrap[i] = 2;
					}
				}
			}
		});
	
		// First Condition of Pause in First Run
		if (canPause == false) {
			addKeyListener(panelMaze);
			timer.start();
			canPause = true;
		}
	}
	
	// Key Listener for Main Frame
	@Override
	public void keyPressed(KeyEvent e) {
		// Space Key Pressed
		if (e.getKeyCode() == e.VK_SPACE) {
			// Condition When Timer will be Paused
			if (canPause == false) {
				// Starting Timer and Adding Key Listener for Panel Maze so Player can be Moved
				addKeyListener(panelMaze);
				timer.start();
				canPause = true;
			}
			
			// Condition When Timer will be Started
			else if (canPause == true) {
				// Stopping Timer and Removing Key Listener for Panel Maze so Player can't be Moved
				removeKeyListener(panelMaze);
				timer.stop();
				canPause = false;
			}
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

