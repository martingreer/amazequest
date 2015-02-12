import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel {
	
	private static final boolean DEBUG = false;
	private static final int MAP_SIZE = 14;
	private static final int TILE_SIZE = 32;
	
	private Map map;
	private Player player;
	
	private ImageResources res = new ImageResources();

	
	public Board(){
		if(DEBUG){System.out.println("DEBUG: Board constructor initiated.");}
		map = new Map();
		player = new Player(1,1,1,10,null);
		setFocusable(true);
	}
	
	public void paint(Graphics g){
		if(DEBUG){System.out.println("DEBUG: Attempting to draw the board.");}
		super.paint(g);
		for(int y=0; y<MAP_SIZE;y++){
			for(int x=0; x<MAP_SIZE; x++){
				g.drawImage(res.getImg(map.getTile(x,y).getImgID()), x*TILE_SIZE, y*TILE_SIZE, null);						
			}
		}
		
		g.drawImage(player.getPlayerImage(), player.getxPos()*TILE_SIZE, player.getyPos()*TILE_SIZE, null);
	}
}

