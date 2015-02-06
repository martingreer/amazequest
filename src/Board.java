import java.awt.*;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel {
	
	private static final boolean DEBUG = true;
	private static final int MAPSIZE = 14;
	private static final int TILESIZE = 32;
	
	private Timer timer;
	private Map map;
	
	public Board(){
		if(DEBUG){System.out.println("DEBUG: Board constructor initiated.");}
		map = new Map();
	}
	
	public void paint(Graphics g){
		if(DEBUG){System.out.println("DEBUG: Attempting to draw the board.");}
		super.paint(g);
		for(int y=0; y<MAPSIZE;y++){
			for(int x=0; x<MAPSIZE; x++){
				if(map.getMap(x, y).equals(".")){
					g.drawImage(map.getGrass(), x*TILESIZE, y*TILESIZE, null);
				}
				if(map.getMap(x, y).equals("W")){
					g.drawImage(map.getWall(), x*TILESIZE, y*TILESIZE, null);
				}
			}
		}
	}
}
