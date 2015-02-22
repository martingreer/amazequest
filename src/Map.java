import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Map extends JPanel {

	private static final boolean DEBUG = false;
	private static final int MAP_SIZE = 14;
	private static final int TILE_SIZE = 32;
	private static final String[][] String = null;

	private Inventory inventory = new Inventory();
	private Scanner m;
	private Tile[][] tiles = new Tile[MAP_SIZE][MAP_SIZE];
	private Player player;
	private Tile playerTile;
	private Tile doorTile;
	private ImageResources res = new ImageResources();
	private Random rand = new Random();               // test random
	private Boolean[][] enemyExists = new Boolean[MAP_SIZE][MAP_SIZE];
	private boolean doorOpen = false;

	public Map(){
		openFile();
		readFile();
		closeFile();

		player = new Player(1,5,10,"player",0);
		tiles[1][1].setPlayer(player);
		playerTile = tiles[1][1];
		discoverDarkness();
		initKeyListener();
		spawnObjectsInitiator();
		setFocusable(true);
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
					else if(c[x] == 'D'){
						tiles[x][y] = new Tile(x, y, "doorClosed", true,true);
						tiles[x][y].setDoor();
						doorTile = tiles[x][y];
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

				if(!(player.getHp() <= 0)) {    // if the player dies... 
					int key = e.getKeyCode();
					System.out.println("Player atk = " +  player.getAttack()  +  " Player hp = " +  player.getHp());
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
			pickUpItem((Item)nextTile.getInterObj());
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
			doorOpen = checkIfAllEnemiesAreDead();		//set to true if all enemies are dead.
		}

		if(player.getHp() <= 0) {
			System.out.println("Player is dead");
			//playerTile.setPlayer(null);
			tiles[playerTile.getXPos()][playerTile.getYPos()] = new Tile(playerTile.getXPos(), 
					playerTile.getYPos(), "blood", false,false);

			Object[] options = {"PLAY AGAIN"};
			int clicked = JOptionPane.showOptionDialog(null,
					"YOU ARE DEAD SUCKA ","GAME OVER",
					JOptionPane.PLAIN_MESSAGE,
					JOptionPane.INFORMATION_MESSAGE,
					null,
					options,
					options[0]);

			if( clicked == JOptionPane.OK_OPTION) {
				GameWindow.setGameFalse();      
				String[] stuff = new String[] {""};
				GameWindow.main(stuff);
				//System.exit(0);
			}
		}
	}

	public void pickUpItem(Item item){
		System.out.println("pickUpItem()");
		inventory.addItem(item);
		player.updateStats(item);
		System.out.println("InventorySize = " + inventory.showSize());
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

	public void spawnObjectsInitiator(){
		// Spawn(type, amount)
		spawnObjectsRandomly("enemyLv1", 4);
		spawnObjectsRandomly("enemyLv2", 3);
		spawnObjectsRandomly("enemyLv3", 3);
		spawnObjectsRandomly("itemSword", 1);
		spawnObjectsRandomly("itemShield", 1);
		spawnObjectsRandomly("itemPotion", 4);
	}

	public void spawnEnemy(int xPos, int yPos, String enemyType){

		if(tiles[xPos][yPos].getCollision()){
			System.out.println("Can not place Enemy on tile with collision");
			return;
		}

		if(enemyType == "enemyLv1"){
			Enemy enemy = new Enemy(1,1,10,"enemyLv1");	//(level,attack,hp,name)
			tiles[xPos][yPos].setEnemy(enemy);
		}

		if(enemyType == "enemyLv2"){ 
			Enemy enemy = new Enemy(2,2,20,"enemyLv2");  
			tiles[xPos][yPos].setEnemy(enemy);
		}
		if(enemyType == "enemyLv3"){
			Enemy enemy = new Enemy(3,3,30,"enemyLv3");
			tiles[xPos][yPos].setEnemy(enemy);
		}
		//more enemy types here? This should be in a config file imo.
	}

	public void spawnItem(int xPos, int yPos, String itemType){

		if(tiles[xPos][yPos].getCollision()){
			System.out.println("Can not place Item on tile with collision");
			return;
		}

		if(itemType == "itemSword"){
			Item item = new Item(1,5,0,"sword");		//Item(level,attack,hp,name)
			tiles[xPos][yPos].setItem(item);
		}

		if(itemType == "itemShield"){ 
			Item item = new Item(1,0,10,"shield");
			tiles[xPos][yPos].setItem(item);
		}

		if(itemType == "itemPotion"){
			Item item = new Item(0,0,7,"potion");
			tiles[xPos][yPos].setItem(item);
		}
		//more item types here?  This should be in a config file imo.
	}

	public void spawnObjectsRandomly(String type, int amount) {

		int xValue = rand.nextInt(13) + 1;
		int yValue = rand.nextInt(13) + 1;

		if( type == "enemyLv1" || type == "enemyLv2" || type == "enemyLv3") {
			for(int i = 0;  i < amount; i++) {

				while(tiles[xValue][yValue].getCollision()) {
					xValue = rand.nextInt(13) +1;
					yValue = rand.nextInt(13) +1;
				}
				if(!(tiles[xValue][yValue] == tiles[1][1])){
					spawnEnemy(xValue, yValue, type);
				}
				xValue = rand.nextInt(13) +1;
				yValue = rand.nextInt(13) +1;
				//System.out.println(xValue);
			}
		}

		if(type == "itemSword" || type == "itemShield" || type == "itemPotion") {
			for(int i = 0;  i < amount; i++) {
				while(tiles[xValue][yValue].getCollision()) {
					xValue = rand.nextInt(13) +1;
					yValue = rand.nextInt(13) +1;
				}
				if(!(tiles[xValue][yValue] == tiles[1][1])){
					spawnItem(xValue, yValue, type);
				}
				xValue = rand.nextInt(13) +1;
				yValue = rand.nextInt(13) +1; // rand 
			}
		}
	}
	
	/**
	 * Checks if there are no enemies the map, if so,
	 * opens the door. 
	 * 
	 * @return true if there is still an enemy on the map, otherwise false.
	 */
	public boolean checkIfAllEnemiesAreDead(){
		for(int y=0; y<MAP_SIZE; y++){
			for(int x=0; x<MAP_SIZE; x++){
				if(tiles[x][y].containsEnemy()){
					return false;
				}
			}
		}
		
		System.out.println("-----------------------------------");
		System.out.println("All enemies are dead. Opening door.");
		System.out.println("-----------------------------------");
		doorTile.setImgID("doorOpened");
		return true;
	}
}

