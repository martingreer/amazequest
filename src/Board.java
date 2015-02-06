import java.awt.*;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel {
	
	private static final boolean DEBUG = false;
	private static final int MAP_SIZE = 14;
	private static final int TILE_SIZE = 32;
	
	private Timer timer;
	private Map map;
	
	public Board(){
		if(DEBUG){System.out.println("DEBUG: Board constructor initiated.");}
		map = new Map();
	}
	
	public void paint(Graphics g){
		if(DEBUG){System.out.println("DEBUG: Attempting to draw the board.");}
		super.paint(g);
		for(int y=0; y<MAP_SIZE;y++){
			for(int x=0; x<MAP_SIZE; x++){
				if(map.getMap(x, y).equals(".")){
					g.drawImage(map.getGrass(), x*TILE_SIZE, y*TILE_SIZE, null);
				}
				if(map.getMap(x, y).equals("W")){
					g.drawImage(map.getWall(), x*TILE_SIZE, y*TILE_SIZE, null);
				}
			}
		}
	}
}
