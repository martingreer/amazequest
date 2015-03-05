package View;


/**
 * This class is responsible for creating a new GameFrame called Amazequest
 * and make it visible.
 * 
 * @author David Fogelberg
 * @version 2015-03-05
 */
public class Main{
	private static GameFrame gameFrame;
	

	/**
	 *@param args does nothing , is needed for main method
	 */

	public static void main(String[] args){
		gameFrame = new GameFrame("Amazequest");
		gameFrame.setVisible(true);
	}
}
