import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.*;

import javax.swing.JPanel;


public class Map extends JPanel {
	
	private static final boolean DEBUG = false;
	private static final int MAP_SIZE = 14;
	private static final int TILE_SIZE = 32;
	
	
	private Scanner m;
	private Tile[][] tiles = new Tile[MAP_SIZE][MAP_SIZE];
	private Player player;
	private Tile playerTile;
	private Item item;
	private Tile itemTile;
	private ImageResources res = new ImageResources();

	public Map(){
		if(DEBUG){System.out.println("DEBUG: Board constructor initiated.");}
		
		
		setFocusable(true);
		
		openFile();
		readFile();
		closeFile();
		
		player = new Player(1,1,5,"player",0);
		tiles[1][1].setPlayer(player);
		playerTile = tiles[1][1];
		
		//spawn enemy
		spawnEnemy(1, 4, 1);
		spawnEnemy(11, 3, 2);
		initKeyListener();
	/*	
		item = new Item(0,0,0,"item");
		tiles[1][2].setItem(item);
		itemTile = tiles[1][2];
	*/
		
		
	}

	public void openFile(){
		try{
			m =  new Scanner(new File("./res/map_1.txt"));
		}catch(Exception e){
			System.out.println("Error: Map failed to load");
		}
	}

	public void readFile(){
		char[] c;
		while(m.hasNext()){
			for(int y=0; y<MAP_SIZE; y++){
				c = m.next().toCharArray();
				for(int x=0; x<MAP_SIZE; x++){
					if(c[x] == 'W'){
						tiles[x][y] = new Tile(x, y, "wall", true);
					}
					else if(c[x] == '.'){
						tiles[x][y] = new Tile(x, y, "grass", false);
					}
				}
			}

		}
	}

	public void closeFile(){
		m.close();
	}
	
	public void paint(Graphics g){
		if(DEBUG){System.out.println("DEBUG: Attempting to draw the board.");}
		super.paint(g);
		for(int y=0; y<MAP_SIZE;y++){
			for(int x=0; x<MAP_SIZE; x++){
				g.drawImage(res.getImg(tiles[x][y].getImgID()), x*TILE_SIZE, y*TILE_SIZE, null);	
				if(tiles[x][y].getInterObj() != null){
					g.drawImage(res.getImg(tiles[x][y].getInterObj().getName()), x*TILE_SIZE, y*TILE_SIZE, null);
				}
			}
		}
	}
	
	private void initKeyListener(){
	      addKeyListener(new KeyAdapter() {
	    	  /*
	          @Override
	          public void keyTyped(KeyEvent e) {
	             myKeyEvt(e, "keyTyped");
	          }
	          
	          @Override
	          public void keyReleased(KeyEvent e) {
	             myKeyEvt(e, "keyReleased");
	          }
	          */
	          @Override
	          public void keyPressed(KeyEvent e) {
	             myKeyEvt(e, "keyPressed");
	          }

	          private void myKeyEvt(KeyEvent e, String text) {
	             int key = e.getKeyCode();
	             Tile nextTile = null;
	             if (key == KeyEvent.VK_KP_LEFT || key == KeyEvent.VK_LEFT)
	             {
	            	 nextTile = tiles[playerTile.getXPos()-1][playerTile.getYPos()];
	             }
	             else if (key == KeyEvent.VK_KP_RIGHT || key == KeyEvent.VK_RIGHT)
	             {
	            	 nextTile = tiles[playerTile.getXPos()+1][playerTile.getYPos()];

	             }
	             else if (key == KeyEvent.VK_KP_UP || key == KeyEvent.VK_UP)
	             {
	            	 nextTile = tiles[playerTile.getXPos()][playerTile.getYPos()-1];
	             }
	             else if (key == KeyEvent.VK_KP_DOWN || key == KeyEvent.VK_DOWN)
	             {
	            	 nextTile = tiles[playerTile.getXPos()][playerTile.getYPos()+1];
	             }
	             
            	 if(!nextTile.getCollision() && nextTile.isEmpty()){
            		 nextTile.setPlayer(player);
            		 playerTile.setPlayer(null);
            		 playerTile = nextTile;
            	 }
	          }
	      });
	}
	
	public void spawnEnemy(int xPos, int yPos, int type){
		
		//(int level, int attack, int hp, String name)
		
		if(type == 1){
			Enemy enemy = new Enemy(1,1,5,"enemyLv1");
			tiles[xPos][yPos].setEnemy(enemy);
		}
		
		if(type == 2){ 
			Enemy enemy = new Enemy(2,2,10,"enemyLv2");  
			tiles[xPos][yPos].setEnemy(enemy);
		}
		
		//more enemy types here?
	
	}
}
