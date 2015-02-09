import javax.swing.JFrame;
import javax.swing.Timer;


public class GameEngine implements Runnable {
	
	private static final boolean DEBUG = false;
	
	private Board board;
	
	public GameEngine(){
		// This constructor is here to prevent a super-constructor
	}

	public GameEngine(Board readBoard, JFrame gamewindow){
		if(DEBUG){System.out.println("DEBUG: Game Engine constructor initiated.");}
		
		//Draw board inside game window
		board = readBoard;
		gamewindow.add(board);
	}
	
	public void run(){
		if(DEBUG){System.out.println("DEBUG: Game Engine thread started.");}
		
		// Here we repaint the board every 25ms (25 fps) 
		while(true){
		    try {
		    	board.repaint();
		        Thread.sleep(25);
		        if(DEBUG){System.out.println("Thread is sleeping for 1 second");}
		    } catch(InterruptedException ie) {}
		}
	}
}