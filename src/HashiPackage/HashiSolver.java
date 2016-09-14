package HashiPackage;

import javax.swing.JOptionPane;

/*************************************************************************************************
 * The Solver for the HashiGame. A User first creates the game in a different class and then 
 * it is solved in this one.
 * @author Mitchell Couturier
 * @version 4/4/2015
 *************************************************************************************************/
public class HashiSolver {

	/** The game that is to be solved**/
	private HashiGame game;
	
	/** Array of all the nodes in the game**/
	private Node[] nodes;
	
	/**the number of nodes in the nodes array**/
	private int numOfNodes;
	
	/**Node used for testing purposes**/
	private Node testNode;
	
	/**true if the game is solved**/
	private boolean isSolved;
	
	/**************************************************************
	 * Constructor for the HashiSolver
	 * @param g the game that is to be solved
	 *************************************************************/
	public HashiSolver(HashiGame g){
		game = g;
		testNode = new Node(0);
		createArray();
		numOfNodes = nodes.length;
		isSolved = false;
		
		//solves
		int n = 1;	//this will represent whatever node connects to nodes[0]
		while(n <= (numOfNodes - 1) && !isSolved){
			if(!solve(nodes[0], nodes[n])){
				n++;
			}else{
				isSolved = true;
			}
		}
		if(!isSolved){
			JOptionPane.showMessageDialog(null, "There is no solution for this Hashi game", "No Solution", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/******************************************************************
	 * Fills the "nodes" array with all of the nodes in the game
	 ******************************************************************/
	public void createArray(){
		Object[][] board = game.getBoard();
		int count = 0;
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				if(board[x][y].getClass().equals(testNode.getClass())){
					count++;
				}
			}
		}
		nodes = new Node[count];
		int num = 0;
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				if(board[x][y].getClass().equals(testNode.getClass())){
					nodes[num] = (Node)board[x][y];
					num++;
				}
			}
		}
	}
	
	/***********************************************************************
	 * Recursive method that uses backtracking to solve the HashiGame. Will
	 * return true when the game is solved.
	 * @return boolean True if solved
	 ***********************************************************************/
	public boolean solve(Node n1, Node n2){
		//before anything, make sure the nodes aren't the same
		if(n1 == n2){
			return false;
		}
		//first step: update the game by adding an edge
		if(game.isValidAdd(n1, n2)){
			game.addEdge(n1, n2);
		}
		else{
			//if this Edge is not a valid add, just return false
			return false;
		}
		if(game.checkIfWon()){
			//if the game is won, return true!
			return true;
		}
		else if(!game.checkIfPossibleMoves()){
			//if there are no possible moves, but the game is not won, return false
			game.removeEdge(n1, n2);
			return false;
		}
		//RECURSIVE STEP
		else{
			//set pnode values to match n1 and n2
			int pnode1 = 0;
			int pnode2 = 0;
			for(int i = 0; i < numOfNodes -1; i++){
				if(n1 == nodes[i])
					pnode1 = i;
				if(n2 == nodes[i])
					pnode2 = i;
			}
			//the actual recursive process
			do{
				//if n2 is the last node in the list, then cycle to the next n1
				if(nodes[pnode1].getDegree() == 0 || pnode2 == numOfNodes - 1){
					do{
						pnode1++;
						pnode2 = pnode1;
					}while(nodes[pnode1].getDegree() == 0 && pnode1 < numOfNodes-1);
				}
				//otherwise..
				else{
					pnode2++;
				}
				//then recursively call for these set of nodes
				if(nodes[pnode1].getNorthNode() == nodes[pnode2] || nodes[pnode1].getEastNode() == nodes[pnode2] || 
						nodes[pnode1].getSouthNode() == nodes[pnode2] || nodes[pnode1].getWestNode() == nodes[pnode2]){
					if(solve(nodes[pnode1], nodes[pnode2])){
						return true;
					}
				}
			}while(!(pnode2 >= (numOfNodes - 2) && pnode1 >= (numOfNodes -1)));

			//if all the solve()s with this edge never return true, check if it will with a double edge, if applicable
			if(!solve(n1, n2)){
				game.removeEdge(n1, n2);
				return false;
			}
		}
		return true;
	}
}
