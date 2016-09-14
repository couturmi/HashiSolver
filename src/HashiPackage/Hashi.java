package HashiPackage;

import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;

public class Hashi {

	/** frame that displays the HashiGame **/
	private static JFrame frame;
	
	public static void main(String [] args){
		frame = new JFrame("Hashi Solver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		HashiGUI panel = new HashiGUI();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocation(1000,150);
	}
	
	/***********************************************************************
	 * Called in the HashiGUI class to resize the frame when needed
	 ***********************************************************************/
	public static void reset(){
		frame.pack();
		frame.setLocation(1000,150);
	}
}
