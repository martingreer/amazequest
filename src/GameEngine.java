import javax.swing.JFrame;
import javax.swing.Timer;


public class GameEngine implements Runnable {
	
	private static final boolean DEBUG = false;
	
	private Map map;
	
	public GameEngine(){
		// This constructor is here to prevent a super-constructor
	}

	public GameEngine(Map readBoard, JFrame gamewindow){
		if(DEBUG){System.out.println("DEBUG: Game Engine constructor initiated.");}
		
		//Draw board inside game window
		map = readBoard;
		gamewindow.add(map);
	}
	
	public void run(){
		if(DEBUG){System.out.println("DEBUG: Game Engine thread started.");}
		
		// Here we repaint the board every 25ms (25 fps) 
		while(true){
		    try {
		    	map.requestFocus(); // Denna kommer sätta fokus tillbaka tillbaka mappen när man trycker i inventoryt. ingen superbra lösning
		    	map.repaint();
		        Thread.sleep(25);
		        if(DEBUG){System.out.println("Thread is sleeping for 1 second");} 
		    } catch(InterruptedException ie) {}
		}
	}
}