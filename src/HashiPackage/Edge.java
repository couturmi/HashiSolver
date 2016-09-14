package HashiPackage;

/*******************************************************************************
 * A very simple class that represents Edges in the Hashi Game
 * @author Mitchell Couturier
 * @version 4/4/2015
 *******************************************************************************/
public class Edge{

	/**represents the different type of edges. (0 = no edge, 1 = one edge, 2 = two edges)**/
	private int edgeType;
	
	/**represents the edge direction. (0 = no edge, 1 = horizontal, 2 = vertical)**/
	private int edgeDirection;
	
	public Edge(){
		edgeType = 0;
		edgeDirection = 0;
	}

	public int getEdgeType() {
		return edgeType;
	}

	public void setEdgeType(int edgeType) {
		this.edgeType = edgeType;
	}

	public int getEdgeDirection() {
		return edgeDirection;
	}

	public void setEdgeDirection(int edgeDirection) {
		this.edgeDirection = edgeDirection;
	}
}
