package HashiPackage;

import javax.swing.JOptionPane;

public class HashiGame {

	/** The game board that contains both Edge and Node objects **/
	private Object[][] board;
	
	/**Node used for testing purposes**/
	private Node testNode;
	
	/**Edge used for testing purposes**/
	private Edge testEdge;
	
	/**************************************************************************
	 * The Constructor for the HashiGame
	 **************************************************************************/
	public HashiGame(){
		testNode = new Node(0);
		board = new Object[10][10];	//we will be playing with an 10 x 10 board
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 10; col++){
				board[row][col] = new Edge();
			}
		}
	}
	
	/*********************************************************************
	 * adds a Node to this Hashi game board
	 * @return int returns 1 if node was added, returns -1 if node was cancelled
	 * @param x the X-Coordinate on the board
	 * @param y the Y-Coordinate on the board
	 *********************************************************************/
	public int addNode(int x, int y){
		String a = JOptionPane.showInputDialog("Enter the degree of this Node");
		//if cancel button was pressed
		if(a == null || (a != null && (a.equals(""))))   
		{
		    return -1;
		}
		int deg;
		try{
			deg = Integer.parseInt(a);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "This is not a valid input", "Not Valid Input", JOptionPane.WARNING_MESSAGE);
			return -1;
		}
		board[x][y] = new Node(deg);
		return 1;
	}
	
	/*****************************************************************************************************
	 * Finds the possible connections that can happen between Nodes and sets the values to each Node's
	 * northNode, eastNode, southNode, and westNode values
	 *****************************************************************************************************/
	public void findPossibleConnections(){
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				//checks if object is a Node
				if(board[x][y].getClass().equals(testNode.getClass())){
					//checks above this Node for other Nodes
					for(int y2 = y - 1; y2 >= 0; y2--){
						if(board[x][y2].getClass().equals(testNode.getClass())){
							((Node) board[x][y]).setNorthNode((Node)board[x][y2]);
							y2 = -1;
						}
					}
					//checks west of this Node for other Nodes
					for(int x2 = x - 1; x2 >= 0; x2--){
						if(board[x2][y].getClass().equals(testNode.getClass())){
							((Node) board[x][y]).setWestNode((Node)board[x2][y]);
							x2 = -1;
						}
					}
					//checks east of this Node for other Nodes
					for(int x2 = x + 1; x2 < 10; x2++){
						if(board[x2][y].getClass().equals(testNode.getClass())){
							((Node) board[x][y]).setEastNode((Node)board[x2][y]);
							x2 = 10;
						}
					}
					//checks below this Node for other Nodes
					for(int y2 = y + 1; y2 < 10; y2++){
						if(board[x][y2].getClass().equals(testNode.getClass())){
							((Node) board[x][y]).setSouthNode((Node)board[x][y2]);
							y2 = 10;
						}
					}
				}
			}
		}
	}
	
	/**********************************************************************************************************
	 * Adds an edge between Node1 and Node2.
	 * @param n1 Node 1
	 * @param n2 Node 2
	 **********************************************************************************************************/
	public void addEdge(Node n1, Node n2){
		//decrements the Nodes' needed degree count
		n1.dec();
		n2.dec();
		//finds the position of n1 on the board
		int x = 0;
		int y = 0;
		while(board[x][y] != n1){
			y = 0;
			while(board[x][y] != n1 && y < 9){
				y++;
			}
			if(board[x][y] != n1)
				x++;
		}
		//updates the edge's value (0, 1, or 2)
		if(n1.getNorthNode() == n2){
			n1.setNorthEdge(n1.getNorthEdge() + 1);
			n2.setSouthEdge(n2.getSouthEdge() + 1);
			for(int y2 = y-1; y2 >= 0; y2--){
				if(board[x][y2] == n2){
					break;
				}
				else{
					((Edge)board[x][y2]).setEdgeType(((Edge)board[x][y2]).getEdgeType() + 1);
					((Edge)board[x][y2]).setEdgeDirection(2);
				}
			}
		}
		else if(n1.getEastNode() == n2){
			n1.setEastEdge(n1.getEastEdge() + 1);
			n2.setWestEdge(n2.getWestEdge() + 1);
			for(int x2 = x+1; x2 < 10; x2++){
				if(board[x2][y] == n2){
					break;
				}
				else{
					((Edge)board[x2][y]).setEdgeType(((Edge)board[x2][y]).getEdgeType() + 1);
					((Edge)board[x2][y]).setEdgeDirection(1);
				}
			}
		}
		else if(n1.getSouthNode() == n2){
			n1.setSouthEdge(n1.getSouthEdge() + 1);
			n2.setNorthEdge(n2.getNorthEdge() + 1);
			for(int y2 = y+1; y2 < 10; y2++){
				if(board[x][y2] == n2){
					break;
				}
				else{
					((Edge)board[x][y2]).setEdgeType(((Edge)board[x][y2]).getEdgeType() + 1);
					((Edge)board[x][y2]).setEdgeDirection(2);
				}
			}
		}
		else if(n1.getWestNode() == n2){
			n1.setWestEdge(n1.getWestEdge() + 1);
			n2.setEastEdge(n2.getEastEdge() + 1);
			for(int x2 = x-1; x2 >= 0; x2--){
				if(board[x2][y] == n2){
					break;
				}
				else{
					((Edge)board[x2][y]).setEdgeType(((Edge)board[x2][y]).getEdgeType() + 1);
					((Edge)board[x2][y]).setEdgeDirection(1);
				}
			}
		}
		else
			System.out.println("Error: HashiGame.addEdge() method: n2 is not a valid parameter for n1");
	}
	
	/***********************************************************************************************************
	 * Removes an edge between Node1 and Node 2. 
	 * @param n1 Node 1
	 * @param n2 Node 2
	 ***********************************************************************************************************/
	public void removeEdge(Node n1, Node n2){
		//increments the Nodes' needed degree counts
		n1.inc();
		n2.inc();
		//finds the position of n1 on the board
		int x = 0;
		int y = 0;
		while(board[x][y] != n1){
			y = 0;
			while(board[x][y] != n1 && y < 9){
				y++;
			}
			if(board[x][y] != n1)
				x++;
		}
		//updates the edge's value (0, 1, or 2)
		if(n1.getNorthNode() == n2){
			n1.setNorthEdge(n1.getNorthEdge() - 1);
			n2.setSouthEdge(n2.getSouthEdge() - 1);
			for(int y2 = y-1; y2 >= 0; y2--){
				if(board[x][y2] == n2){
					break;
				}
				else{
					((Edge)board[x][y2]).setEdgeType(((Edge)board[x][y2]).getEdgeType() - 1);
					if(((Edge)board[x][y2]).getEdgeType() == 0)
						((Edge)board[x][y2]).setEdgeDirection(0);
				}
			}
		}
		else if(n1.getEastNode() == n2){
			n1.setEastEdge(n1.getEastEdge() - 1);
			n2.setWestEdge(n2.getWestEdge() - 1);
			for(int x2 = x+1; x2 < 10; x2++){
				if(board[x2][y] == n2){
					break;
				}
				else{
					((Edge)board[x2][y]).setEdgeType(((Edge)board[x2][y]).getEdgeType() - 1);
					if(((Edge)board[x2][y]).getEdgeType() == 0)
						((Edge)board[x2][y]).setEdgeDirection(0);
				}
			}
		}
		else if(n1.getSouthNode() == n2){
			n1.setSouthEdge(n1.getSouthEdge() - 1);
			n2.setNorthEdge(n2.getNorthEdge() - 1);
			for(int y2 = y+1; y2 < 10; y2++){
				if(board[x][y2] == n2){
					break;
				}
				else{
					((Edge)board[x][y2]).setEdgeType(((Edge)board[x][y2]).getEdgeType() - 1);
					if(((Edge)board[x][y2]).getEdgeType() == 0)
						((Edge)board[x][y2]).setEdgeDirection(0);
				}
			}
		}
		else if(n1.getWestNode() == n2){
			n1.setWestEdge(n1.getWestEdge() - 1);
			n2.setEastEdge(n2.getEastEdge() - 1);
			for(int x2 = x-1; x2 >= 0; x2--){
				if(board[x2][y] == n2){
					break;
				}
				else{
					((Edge)board[x2][y]).setEdgeType(((Edge)board[x2][y]).getEdgeType() - 1);
					if(((Edge)board[x2][y]).getEdgeType() == 0)
						((Edge)board[x2][y]).setEdgeDirection(0);
				}
			}
		}
		else
			System.out.println("Error: HashiGame.addEdge() method: n2 is not a valid parameter for n1");
	}
	
	/*************************************************************************
	 * Returns true if game is won
	 * @return boolean Status of the Hashi Game (True = Won; False = Not Finished)
	 *************************************************************************/
	public boolean checkIfWon(){
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				if(board[x][y].getClass().equals(testNode.getClass())){
					if(((Node) board[x][y]).getDegree() > 0){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/***************************************************************************************
	 * Used after the checkIfWon() returns false to check if there are even any
	 * possible moves left. If not, the current state of the game is not a 
	 * solution.
	 * @return boolean True if there are possible moves left in the game
	 ***************************************************************************************/
	public boolean checkIfPossibleMoves(){
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				if(board[x][y].getClass().equals(testNode.getClass())){
					if(((Node)board[x][y]).getDegree() > 0){
						//if there is a valid move with this node, return true
						if(isValidAdd((Node) board[x][y], ((Node) board[x][y]).getNorthNode())){
							return true;
						}
						if(isValidAdd((Node) board[x][y], ((Node) board[x][y]).getEastNode())){
							return true;
						}
						if(isValidAdd((Node) board[x][y], ((Node) board[x][y]).getSouthNode())){
							return true;
						}
						if(isValidAdd((Node) board[x][y], ((Node) board[x][y]).getWestNode())){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/*************************************************************************************
	 * Returns true if you are able to add an edge between these nodes
	 * @return boolean True if you are able to add an edge between these nodes
	 *************************************************************************************/
	public boolean isValidAdd(Node n1, Node n2){
		//if either node has already reached its MAX degree, or is null, return false
		if(n1 == null || n2 == null){
			return false;
		}
		if(n1.getDegree() == 0 || n2.getDegree() == 0){
			return false;
		}
		//returns false if the nodes are not related
		if(n1.getNorthNode() != n2 && n1.getEastNode() != n2 && n1.getSouthNode() != n2 && n1.getWestNode() != n2){
			return false;
		}
		//finds the position of n1 on the board
		int x = 0;
		int y = 0;
		while(board[x][y] != n1){
			y = 0;
			while(board[x][y] != n1 && y < 9){
				y++;
			}
			if(board[x][y] != n1)
				x++;
		}
		//returns false if the edge to be added is already at a value of 2
		if(n1.getNorthNode() == n2){
			if(n1.getNorthEdge() == 2)
				return false;
			else{
				//returns false if there is already an edge crossing between these two nodes
				for(int y2 = y-1; y2 >= 0; y2--){
					if(board[x][y2] == n2){
						break;
					}
					else{
						if(((Edge)board[x][y2]).getEdgeDirection() == 1)
							return false;
					}
				}
			}
		}
		if(n1.getEastNode() == n2){
			if(n1.getEastEdge() == 2)
				return false;
			else{
				//returns false if there is already an edge crossing between these two nodes
				for(int x2 = x+1; x2 < 10; x2++){
					if(board[x2][y] == n2){
						break;
					}
					else{
						if(((Edge)board[x2][y]).getEdgeDirection() == 2)
							return false;
					}
				}
			}
		}
		if(n1.getSouthNode() == n2){
			if(n1.getSouthEdge() == 2)
				return false;
			else{
				//returns false if there is already an edge crossing between these two nodes
				for(int y2 = y+1; y2 < 10; y2++){
					if(board[x][y2] == n2){
						break;
					}
					else{
						if(((Edge)board[x][y2]).getEdgeDirection() == 1)
							return false;
					}
				}
			}
		}
		if(n1.getWestNode() == n2){
			if(n1.getWestEdge() == 2)
				return false;
			else{
				//returns false if there is already an edge crossing between these two nodes
				for(int x2 = x-1; x2 >= 0; x2--){
					if(board[x2][y] == n2){
						break;
					}
					else{
						if(((Edge)board[x2][y]).getEdgeDirection() == 2)
							return false;
					}
				}
			}
		}
		
		return true;
	}
	
	/******************************************************************************************
	 * Returns the board
	 * @return board
	 ******************************************************************************************/
	public Object[][] getBoard(){
		return board;
	}
}
