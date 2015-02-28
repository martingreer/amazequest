package View;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import Model.ImageResources;
import Model.Map;
import Model.Player;
import Model.Tile;

public class MapPanel extends JPanel {

		private Map map;
		private static final boolean DEBUG = false;
		private static final int MAP_SIZE = 20;
		private static final int TILE_SIZE = 32;
		private ImageResources res = new ImageResources();
		private StatusPanel statusPanel;
		
		public MapPanel(){
			initKeyListener();
		}
		
		public MapPanel(StatusPanel statusPanel){
			this.statusPanel = statusPanel;
			initKeyListener();
		}

		public void createMap(int mapNr, int playerNr){
			map = new Map(mapNr,playerNr);
		}
		
		public Map getMap(){
			return map;
		}
		
		public void paint(Graphics g){
			if(DEBUG){System.out.println("DEBUG: Attempting to draw the board.");}
			super.paint(g);
			Tile[][] tiles = map.getTiles();
			for(int y=0; y<MAP_SIZE;y++){
				for(int x=0; x<MAP_SIZE; x++){
					g.drawImage(res.getImg(tiles[x][y].getImgID()), x*TILE_SIZE, y*TILE_SIZE, null);
					if(tiles[x][y].isBlood()){
						g.drawImage(res.getImg("blood"), x*TILE_SIZE, y*TILE_SIZE, null);
					}
					if(tiles[x][y].getInterObj() != null){
						g.drawImage(res.getImg(tiles[x][y].getInterObj().getName()), x*TILE_SIZE, y*TILE_SIZE, null);
					}
					if(tiles[x][y].isDark()){
						g.drawImage(res.getImg("debugDark"), x*TILE_SIZE, y*TILE_SIZE, null);
					}
				}
			}
		}
		
		private void initKeyListener(){
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					myKeyEvt(e, "keyPressed");
				}

				private void myKeyEvt(KeyEvent e, String text) {			
						int key = e.getKeyCode();
						if (key == KeyEvent.VK_KP_LEFT || key == KeyEvent.VK_LEFT) 
							 map.pressedKey("left");
						 else if (key == KeyEvent.VK_KP_RIGHT || key == KeyEvent.VK_RIGHT) 
							 map.pressedKey("right");
						 else if (key == KeyEvent.VK_KP_UP || key == KeyEvent.VK_UP) 
							 map.pressedKey("up");
						 else if (key == KeyEvent.VK_KP_DOWN || key == KeyEvent.VK_DOWN) 
							 map.pressedKey("down");
						
						updateView();
				}
			});
		}
		
		public void updateView(){
			requestFocus(); // Denna kommer sätta fokus tillbaka tillbaka mappen när man trycker i inventoryt. ingen superbra lösning
	    	repaint();
	    	statusPanel.updatePanel(getMap().getPlayer(),getMap().getCurrentEnemy());
		}
}
