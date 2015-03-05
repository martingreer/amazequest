package View;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import Model.ImageResources;
import Model.GameLogic;
import Model.Player;
import Model.Tile;

/**
 * 	This class is responsible for rendering the the complete map along
 *	with the player and all objects.
 * 
 *  When constructed it will also initiate a keylistener which is 
 *  responsible for the movement of the player object. After each keypress the view vill be updated.
 * 
 * @author Henrik Numé
 * @version 2015-03-05
 */
public class MapPanel extends JPanel {
	
		/**
		 * Reference to GameLogic contains all information about the tiles to be painted.
		 */
		private GameLogic map;
		
		/**
		 * DEBUG
		 */
		private static final boolean DEBUG = false;
		
		/**
		 * Number of tiles of the map in X and Y axis (always square shape).
		 */
		private static final int MAP_SIZE = 20;
		
		/**
		 * Number of pixels in each tile on X and Y axis.
		 */
		private static final int TILE_SIZE = 32;
		
		/**
		 * Keeps track of and gives access to the actual image files used for painting.
		 */
		private ImageResources res = new ImageResources();
		
		/**
		 * Constructor will create the MapPanel and initiate the Keylistener. 
		 * MapPanel extends JPanel so it can be the area used painting all the game images.
		 */
		public MapPanel(){
			initKeyListener();
		}
		
		/**
		 * Creates a new map , the type is decided from mapNr and playerNr  
		 * 
		 *  @param mapNr The number of the map to be created
		 *  @param playerNr The number of the player to be used in the map
		 */
		public void createMap(int mapNr, int playerNr){
			map = new GameLogic(mapNr,playerNr);
		}
		
		/**
		 * Getter for map
		 * 
		 * @return The map as an GameLogic reference
		 */
		public GameLogic getMap(){
			return map;
		}
		
		/**
		 * This will paint the whole map by going through a double array(MAP_SIZE*MAP_SIZE) of tiles which is returned by map.
		 * For each tile all of its content will be drawn on the MapPanel on at the time.
		 * 
		 * @param Graphics g
		 */
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
						g.drawImage(res.getImg("dark"), x*TILE_SIZE, y*TILE_SIZE, null);
					}
				}
			}
		}
		
		/**
		 *	Initiate a Key Listener to be used for moving the player
		 * 	Arrow keys will be mapped to player movement (left,right,up,down)
		 */
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
		
		/**
		 *	This will repaint the MapPanel view and also call StatusPanel to update itself. 
		 * 
		 */
		public void updateView(){
			requestFocus();
	    	repaint();
	    	StatusPanel.updatePanel(getMap().getPlayer(),getMap().getCurrentEnemy());
		}
}
