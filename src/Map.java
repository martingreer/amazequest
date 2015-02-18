import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.*;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Map extends JPanel {
	
	private static final boolean DEBUG = false;
	private static final int MAP_SIZE = 14;
	private static final int TILE_SIZE = 32;
	
	
	private Scanner m;
	private Tile[][] tiles = new Tile[MAP_SIZE][MAP_SIZE];
	private Player player;
	private Tile playerTile;
	private ImageResources res = new ImageResources();
	private Random rand = new Random();               // test random 

	public Map(){
		if(DEBUG){System.out.println("DEBUG: Board constructor initiated.");}
		
		
		setFocusable(true);
		
		openFile();
		readFile();
		closeFile();
		
		player = new Player(1,5,10,"player",0);
		tiles[1][1].setPlayer(player);
		playerTile = tiles[1][1];
		discoverDarkness();
		
		//spawnEnemy(RandomObject(), RandomObject(), 1);
		//spawnEnemy(RandomObject(),RandomObject(), 2);
		//spawnItem(RandomObject(), RandomObject(), 1);
		//spawnItem(RandomObject(), RandomObject(), 2);
		
		RandomObject(1, 2);
		RandomObject(2, 3);
		RandomObject(3, 1);
		RandomObject(4, 3);
		
		
		initKeyListener();

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
						tiles[x][y] = new Tile(x, y, "wall", true,true);
					}
					else if(c[x] == '.'){
						tiles[x][y] = new Tile(x, y, "grass", false,true);
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
				if(tiles[x][y].isDark()){
					g.drawImage(res.getImg("dark"), x*TILE_SIZE, y*TILE_SIZE, null);
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
					
					if (key == KeyEvent.VK_KP_LEFT || key == KeyEvent.VK_LEFT) {
						decideAction(tiles[playerTile.getXPos() - 1][playerTile.getYPos()]);
						player.setName("playerWest");	//set image for each direction

					} else if (key == KeyEvent.VK_KP_RIGHT || key == KeyEvent.VK_RIGHT) {
						decideAction(tiles[playerTile.getXPos() + 1][playerTile.getYPos()]);
						player.setName("playerEast");

					} else if (key == KeyEvent.VK_KP_UP || key == KeyEvent.VK_UP) {
						decideAction(tiles[playerTile.getXPos()][playerTile.getYPos() - 1]);
						player.setName("playerNorth");

					} else if (key == KeyEvent.VK_KP_DOWN || key == KeyEvent.VK_DOWN) {
						decideAction(tiles[playerTile.getXPos()][playerTile.getYPos() + 1]);
						player.setName("playerSouth");
					}	
				}
			});
		}
	
	public void discoverDarkness(){
		
		int x = playerTile.getXPos();
		int y = playerTile.getYPos();
		
		tiles[x-1][y+1].setDarkness(false);
		tiles[x]  [y+1].setDarkness(false);
		tiles[x+1][y+1].setDarkness(false);
		
		tiles[x-1][y].setDarkness(false);
		tiles[x]  [y].setDarkness(false);
		tiles[x+1][y].setDarkness(false);
		
		tiles[x-1][y-1].setDarkness(false);
		tiles[x]  [y-1].setDarkness(false);
		tiles[x+1][y-1].setDarkness(false);

	}
	
	public void decideAction(Tile nextTile){
		
		if(nextTile.containsEnemy()){
			fight(nextTile);
			
		}else if(nextTile.containsItem()){
			pickUpItem();
			removeItem(nextTile);
			
		}else if(nextTile.isEmpty()){
			movePlayerTo(nextTile);
			discoverDarkness();
		}
	}
	
	public void fight(Tile nextTile){
		System.out.println("fighting");
		player.exchangeHitsWithEnemy(nextTile);
		if((nextTile.getInterObj().getHp()) <= 0 ){		//check enemy Hp, remove if <= 0.
			System.out.println("enemy is killed");
			nextTile.setEnemy(null);
		}
	}
	
	public void pickUpItem(){
		System.out.println("pickUpItem()");
	}
	
	public void removeItem(Tile nextTile){
		nextTile.setItem(null);
	}
	
	private void movePlayerTo(Tile nextTile) {
		
			if(nextTile.isEmpty() && !nextTile.getCollision()) {
				nextTile.setPlayer(player);
				playerTile.setPlayer(null);
				playerTile = nextTile;
			}else{
				System.out.println("movePayerTo() failed");
			}
	}
	
	public void spawnEnemy(int xPos, int yPos, int enemyType){
		
		if(tiles[xPos][yPos].getCollision()){
			System.out.println("Can not place Enemy on tile with collision");
			return;
		}
		
		if(enemyType == 1){
			Enemy enemy = new Enemy(1,1,10,"enemyLv1");	//(level,attack,hp,name)
			tiles[xPos][yPos].setEnemy(enemy);
		}
		
		if(enemyType == 2){ 
			Enemy enemy = new Enemy(2,2,20,"enemyLv2");  
			tiles[xPos][yPos].setEnemy(enemy);
		}
		//more enemy types here? This should be in a config file imo.
	}
	
	public void spawnItem(int xPos, int yPos, int itemType){
			
		if(tiles[xPos][yPos].getCollision()){
			System.out.println("Can not place Item on tile with collision");
			return;
		}
	
		if(itemType == 3){
			Item item = new Item(1,5,0,"sword");		//Item(level,attack,hp,name)
			tiles[xPos][yPos].setItem(item);
		}
		
		if(itemType == 4){ 
			Item item = new Item(1,0,10,"shield");  
			tiles[xPos][yPos].setItem(item);
		}
		//more item types here?  This should be in a config file imo.
	}
	public void RandomObject(int type, int amount) {
		
		int xValue = rand.nextInt(13) +1;
		int yValue = rand.nextInt(13) +1;
		
		
		
		if( type == 1 || type == 2) {
		for(int i = 0;  i<amount; i++) {
		while(tiles[xValue][yValue].getCollision()) {
		
			xValue = rand.nextInt(13) +1;
			yValue = rand.nextInt(13) +1;
			
		
			}
		 spawnEnemy(xValue, yValue, type);
		 
		}
		
		}
	
		
		if(type == 3 || type == 4) {
			
			for(int i = 0;  i<amount; i++) {
		
			while(tiles[xValue][yValue].getCollision()) {
				
				xValue = rand.nextInt(13) +1;
				yValue = rand.nextInt(13) +1;
				
			
				}
			 spawnItem(xValue, yValue, type);
			 
			}
			
			}
		
		
		
		}
		
}

