package HashiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HashiGUI extends JPanel{

	/**The HashiGame being played**/
	private HashiGame game;
	
	/**The game board of JButtons, that represents Nodes**/
	private JButton[][] board;
	
	/**Used for visual appeal, a game board of painted components**/
	private HashiPaintedComponent[][] paintedBoard;
	
	/**JButton ActionListener for the GUI**/
	private ButtonListener m1;
	
	/**Activates the solver to solve the game**/
	private JButton solveButton;
	
	/**Sets all of the Nodes in the game as final and ready to play**/
	private JButton setButton;
	
	/**Resets the HashiGame to set up a new one**/
	private JButton resetButton;
	
	/**Determines if the game is set or not**/
	private boolean isSet;
	
	/**Title for the GUI**/
	private JLabel titleLabel;
	
	/**The panel that contains the board of JButtons**/
	private JPanel boardPanel;
	
	/**The panel that contains the game function JButtons**/
	private JPanel functionPanel;
	
	/**The panel that contains the title of the game**/
	private JPanel titlePanel;
	
	/**The panel that contains all of the other panels**/
	private JPanel masterPanel;
	
	/**Node used for testing purposes**/
	private Node testNode;
	
	/**solver for the HashiGame**/
	private HashiSolver solver;
	
	/***************************************************************************
	 * Constructor for the HashiGUI class, which creates the GUI
	 ***************************************************************************/
	public HashiGUI(){
		//initializes all of the GUI and game objects
		game = new HashiGame();
		
		testNode = new Node(0);
		
		boardPanel = new JPanel();
		functionPanel = new JPanel();
		titlePanel = new JPanel();
		masterPanel = new JPanel();
		board = new JButton[10][10];
		paintedBoard = new HashiPaintedComponent[10][10];
		solveButton = new JButton("Solve");
		setButton = new JButton("Set");
		resetButton = new JButton("Reset");
		titleLabel = new JLabel("Hashi Game Solver");
		titleLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 24));
		
		m1 = new ButtonListener();
		isSet = false;
		
		//adds the ButtonListener to JButtons
		solveButton.addActionListener(m1);
		setButton.addActionListener(m1);
		resetButton.addActionListener(m1);
		
		//creates the board of JButtons
		createBoard();
	
		//sets the layout and color of JPanels
		masterPanel.setLayout(new BorderLayout(10,10));
		masterPanel.setBackground(Color.blue);
		setBackground(Color.blue);
		functionPanel.setBackground(Color.LIGHT_GRAY);
		titlePanel.setBackground(Color.LIGHT_GRAY);
		boardPanel.setBackground(Color.yellow);
		
		//puts all of the JPanels and JButtons together
		titlePanel.add(titleLabel);
		functionPanel.add(setButton);
		functionPanel.add(solveButton);
		functionPanel.add(resetButton);
		masterPanel.add(BorderLayout.NORTH, titlePanel);
		masterPanel.add(BorderLayout.CENTER, boardPanel);
		masterPanel.add(BorderLayout.SOUTH, functionPanel);
		add(masterPanel);
	}
	
	/***************************************************************************
	 * Creates the game board of JButtons
	 ***************************************************************************/
	public void createBoard(){
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 10; col++){
				board[row][col] = new JButton("");
				board[row][col].setPreferredSize(new Dimension(50,50));
				board[row][col].addActionListener(m1);
				boardPanel.add(board[row][col]);
			}
		}
		boardPanel.setLayout(new GridLayout(10,10));
		repaint();
		Hashi.reset();
	}
	
	/***************************************************************************
	 * Clears the current board for the GUI
	 ***************************************************************************/
	public void clearBoard(){
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 10; col++){
				boardPanel.remove(board[row][col]);
			}
		}
	}
	
	/***************************************************************************
	 * Clears the current paintedBoard for the GUI
	 ***************************************************************************/
	public void clearPaintedBoard(){
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 10; col++){
				boardPanel.remove(paintedBoard[row][col]);
			}
		}
	}
	
	/**************************************************************************************
	 * Called after the setButton is clicked, and the game is set to be visually appealing
	 **************************************************************************************/
	public void setBoard(){
		HashiPCType type;
		String deg;
		
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 10; col++){
				//determines the type of component to be displayed
				//if object is a Node
				if(game.getBoard()[row][col].getClass().equals(testNode.getClass())){
					type = HashiPCType.node;
					deg = ((Node)game.getBoard()[row][col]).getMAXDegree() + "";
					paintedBoard[row][col] = new HashiPaintedComponent(type, deg);
				}
				//if object is a single Edge
				else if(((Edge)game.getBoard()[row][col]).getEdgeDirection() == 2){
					if(((Edge)game.getBoard()[row][col]).getEdgeType() == 1){
						type = HashiPCType.horizontalEdge1;
						paintedBoard[row][col] = new HashiPaintedComponent(type, null);
					}
					else if(((Edge)game.getBoard()[row][col]).getEdgeType() == 2){
						type = HashiPCType.horizontalEdge2;
						paintedBoard[row][col] = new HashiPaintedComponent(type, null);
					}
				}
				//if object is a double Edge
				else if(((Edge)game.getBoard()[row][col]).getEdgeDirection() == 1){
					if(((Edge)game.getBoard()[row][col]).getEdgeType() == 1){
						type = HashiPCType.verticalEdge1;
						paintedBoard[row][col] = new HashiPaintedComponent(type, null);
					}
					else if(((Edge)game.getBoard()[row][col]).getEdgeType() == 2){
						type = HashiPCType.verticalEdge2;
						paintedBoard[row][col] = new HashiPaintedComponent(type, null);
					}
				}
				else{
					type = HashiPCType.blank;
					paintedBoard[row][col] = new HashiPaintedComponent(type, null);
				}
				//then add this component to the GUI
				boardPanel.add(paintedBoard[row][col]);
			}
		}
		repaint();
		Hashi.reset();
	}
	
	/******************************************************************************
	 * ActionListener class for the GUI
	 ******************************************************************************/
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//for adding Nodes to the board of JButtons
			for(int row = 0; row < 10; row++){
				for(int col = 0; col < 10; col++){
					if(board[row][col] == e.getSource()){
						int added = game.addNode(row, col);
						if(added == 1)
							board[row][col].setText(""+ ((Node)game.getBoard()[row][col]).getMAXDegree());
					}
				}
			}
			//for game functional JButtons
			if(e.getSource() == solveButton){
				if(!isSet){
					JOptionPane.showMessageDialog(null, "You must Set the game before you can continue", 
							"Game not Set", JOptionPane.WARNING_MESSAGE);
				}
				else{
					solver = new HashiSolver(game);
					clearPaintedBoard();
					setBoard();
				}
			}
			else if(e.getSource() == setButton){
				if(!isSet){
					isSet = true;
					game.findPossibleConnections();
					clearBoard();
					setBoard();
				}
			}
			else if(e.getSource() == resetButton){
				game = new HashiGame();
				if(isSet)
					clearPaintedBoard();
				else
					clearBoard();
				createBoard();
				isSet = false;
			}
		}
	}
}
