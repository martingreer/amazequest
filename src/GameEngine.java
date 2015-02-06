import javax.swing.JFrame;


public class GameEngine implements Runnable {
	
	private static final boolean DEBUG = true;
	
	private Board board;
	
	public GameEngine(){
		
	}
	
	public GameEngine(Board readBoard){
		if(DEBUG){System.out.println("DEBUG: Game Engine constructor initiated.");}
		board = readBoard;
		JFrame mainframe = new MainFrame("A Maze Game");
		mainframe.add(board);
		mainframe.setVisible(true);
	}
	
	public void run(){
		if(DEBUG){System.out.println("DEBUG: Game Engine thread started.");}
		// TODO: Code for repainting board every ~25ms
		for(int i = 0; i < 10; i++) {
		    try {
		    	board.repaint();
		        Thread.sleep(1000);
		        System.out.println("Thread has slept " + i + " times");
		    } catch(InterruptedException ie) {}
		}
	}
}
