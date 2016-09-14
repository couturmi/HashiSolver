package HashiPackage;

/***********************************************************************
 * Represents the vertices (or Nodes) in the Hashi game
 * @author Mitchell Couturier
 * @version 3/14/2015
 **********************************************************************/
public class Node {

	/** represents the degree of the Node, as in how many edges should be connected to it**/
	private int MAXdegree;
	
	/** represents how many more edges should be connected to this node to match the MAXdegree**/
	private int degree;
	
	/** the Nodes that this Node can be connected to**/
	private Node northNode, eastNode, southNode, westNode;
	
	/** the status for an edges between two nodes. 0 = no edge, 1 = single, 2 = double**/
	private int northEdge, eastEdge, southEdge, westEdge;
	
	/********************************************************************
	 * Constructor for the Node Class. Sets the degree of the Node
	 * @param d Degree of the Node
	 ********************************************************************/
	public Node(int d){
		MAXdegree = d;
		degree = d;
		
		//the Node starts out not connected to any other Nodes
		northEdge = 0;
		eastEdge = 0;
		southEdge = 0;
		westEdge = 0;
	}
	
	/**********************************************************************
	 * Decrements the degree by 1
	 **********************************************************************/
	public void dec(){
		degree--;
	}
	
	/**********************************************************************
	 * Increments the degree by 1
	 **********************************************************************/
	public void inc(){
		degree++;
	}
	
	public int getDegree(){
		return degree;
	}
	
	public int getMAXDegree(){
		return MAXdegree;
	}
	
	public void setNorthNode(Node n){
		northNode = n;
	}
	
	public Node getNorthNode(){
		return northNode;
	}
	
	public void setEastNode(Node n){
		eastNode = n;
	}
	
	public Node getEastNode(){
		return eastNode;
	}
	
	public void setSouthNode(Node n){
		southNode = n;
	}
	
	public Node getSouthNode(){
		return southNode;
	}
	
	public void setWestNode(Node n){
		westNode = n;
	}
	
	public Node getWestNode(){
		return westNode;
	}

	public int getNorthEdge() {
		return northEdge;
	}

	public void setNorthEdge(int northEdge) {
		this.northEdge = northEdge;
	}

	public int getEastEdge() {
		return eastEdge;
	}

	public void setEastEdge(int eastEdge) {
		this.eastEdge = eastEdge;
	}

	public int getSouthEdge() {
		return southEdge;
	}

	public void setSouthEdge(int southEdge) {
		this.southEdge = southEdge;
	}

	public int getWestEdge() {
		return westEdge;
	}

	public void setWestEdge(int westEdge) {
		this.westEdge = westEdge;
	}

}