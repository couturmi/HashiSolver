package HashiPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

/*****************************************************************************************
 * Used to make the HashiGUI visually appealing by making it look like a real HashiGame
 * @author Mitchell Couturier
 * @version 4/4/2015
 *****************************************************************************************/
public class HashiPaintedComponent extends JButton {

	/**Determines the type of component**/
	private HashiPCType type;
	
	/**text displayed on Node components**/
	private String deg;
	
	/****************************************************************
	 * Constructor
	 * @param t type of component
	 * @param d degree of the node, if applicable
	 ****************************************************************/
	public HashiPaintedComponent(HashiPCType t, String d){
		super();
		setEnabled(false);
		setVisible(true);
		type = t;
		deg = d;
		setPreferredSize(new Dimension(50,50));
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, 50, 50);
		if(type == HashiPCType.node){
			g.setColor(Color.black);
			g.drawOval(0, 0, 49, 49);
			g.drawString(deg, 22, 29);
		}
		else if(type == HashiPCType.horizontalEdge1){
			g.setColor(Color.black);
			g.fillRect(0, 20, 50, 10);
		}
		else if(type == HashiPCType.verticalEdge1){
			g.setColor(Color.black);
			g.fillRect(20, 0, 10, 50);
		}
		else if(type == HashiPCType.horizontalEdge2){
			g.setColor(Color.black);
			g.fillRect(0, 10, 50, 10);
			g.fillRect(0, 30, 50, 10);
		}
		else if(type == HashiPCType.verticalEdge2){
			g.setColor(Color.black);
			g.fillRect(10, 0, 10, 50);
			g.fillRect(30, 0, 10, 50);
		}
		setBorderPainted(false);
	}
	
}
