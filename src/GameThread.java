
public class GameThread implements Runnable {
	
	private static final boolean DEBUG = false;
	
	private MapPanel mapPanel;
	private StatusPanel statusPanel;
	public GameThread(){
		// This constructor is here to prevent a super-constructor
	}

	public GameThread(MapPanel mapPanel, StatusPanel statusPanel){
		if(DEBUG){System.out.println("DEBUG: Game Engine constructor initiated.");}
		
		//Draw board inside game window
		this.mapPanel = mapPanel;
		this.statusPanel = statusPanel;
	}
	
	public void run(){
		if(DEBUG){System.out.println("DEBUG: Game Engine thread started.");}
		
		// Here we repaint the board every 25ms (25 fps) 
		while(true){
		    try {
		    	mapPanel.requestFocus(); // Denna kommer sätta fokus tillbaka tillbaka mappen när man trycker i inventoryt. ingen superbra lösning
		    	mapPanel.repaint();
		    	statusPanel.updatePanel(mapPanel.getMap().getPlayer(),mapPanel.getMap().getCurrentEnemy());
		        Thread.sleep(25);
		        if(DEBUG){System.out.println("Thread is sleeping for 1 second");} 
		    } catch(InterruptedException ie) {}
		}
	}
}