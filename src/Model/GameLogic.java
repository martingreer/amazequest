package Model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.swing.*;

import View.GameFrame;
import View.StatusPanel;

/**
 * This class is initiated when starting a new game and creates the logical structure of the map
 * and its objects. It is also the core of all the in-game logical functions.
 * 
 * @author Martin Greer
 * @version 2015-03-03
 *
 */
public class GameLogic{
	
	/**
	 * Number of tiles of the map in X and Y axis (always square shape).
	 */
	private static final int MAP_SIZE = 20;
	
	/**
	 * Map ID (1/2/3).
	 */
	private int mapNr;
	
	/**
	 * File scanner.
	 */
	private Scanner m;
	
	/**
	 * Array that contains each tile on the map.
	 */
	private Tile[][] tiles = new Tile[MAP_SIZE][MAP_SIZE];
	
	/**
	 * The user-controlled player.
	 */
	private Player player;
	
	/**
	 * The enemy that the player is currently interacting with.
	 */
	private Enemy currentEnemy;
	
	/**
	 * Keeps track of which tile the player is currently on.
	 */
	private Tile playerTile;
	
	/**
	 * Keeps track of which tile the door is located on. 
	 */
	private Tile doorTile;
	
	/**
	 * A random number generator.
	 */
	private Random rand = new Random();
	
	/**
	 * Keeps track of the status of the door (true = open, false = closed).
	 */
	private Boolean doorOpen = false;
	
	/**
	 * The player ID (1 or 2).
	 */
	private int playerNr;
	
	/**
	 * Constructor that will read a map text file and, if it exists, a saved player object.
	 * Creates a new player with base stats if a save file does not exist. The player always
	 * spawns on a default tile on game start (top left corner).
	 * 
	 * @param mapNr The ID of the map that the player selected.
	 * @param playerNr The ID of the chosen player character.
	 */
	public GameLogic(int mapNr, int playerNr){
		this.mapNr = mapNr;
		this.playerNr = playerNr;
		currentEnemy = null;
		openFile();
		readFile();
		closeFile();
		load(playerNr);
		if(player == null){
			player = new Player(1,5,10,10,"player"+playerNr,0);
		}
		player.healToFull();
		tiles[1][1].setPlayer(player);
		playerTile = tiles[1][1];
		StatusPanel.updatePanel(player, currentEnemy);
		discoverDarkness();
		spawnObjectsInitiator(mapNr);
	}
	
	/**
	 * Saves the current state of the player object into a local file on the
	 * users file system. This function is only called when a map is completed
	 * by walking through the open door.
	 * 
	 * @param playerId The ID of the player character to be saved.
	 */
	public void save(int playerId) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./bin/SavedPlayer" + playerId));
            out.writeObject(player);
            out.close();
            System.out.println("Player saved");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
   
	/**
	 * Attempts to load a player state from a local file and puts those values
	 * into the current player object. This function is only called upon map
	 * creation.
	 * 
	 * @param playerId The ID of the player character to be loaded.
	 */
    public void load(int playerId) {
        try {
        	player = null;
        	File file = new File("./bin/SavedPlayer" + playerId);
        	if (file.exists()) {
            	ObjectInputStream in = new ObjectInputStream(new FileInputStream("./bin/SavedPlayer" + playerId));
                player = (Player)in.readObject();
                in.close();
                System.out.println("Player loaded");     
        	}
        } catch (Exception e) {
        	System.out.println("LOAD FAILED \n");
            e.printStackTrace();
		}
    }
    
    /**
     * Registers which direction the player wants to go next and calls the function
     * that decides which action to perform for the next tile.
     * 
     * @param key The key that was pressed.
     */
	public void pressedKey(String key){
		if(key.equals("left")){
			decideAction(tiles[playerTile.getXPos() - 1][playerTile.getYPos()]);
			player.setName("player"+playerNr+"West");	//set image for each direction
		}
		else if(key.equals("right")){
			decideAction(tiles[playerTile.getXPos() + 1][playerTile.getYPos()]);
			player.setName("player"+playerNr+"East");
		}
		else if(key.equals("up")){
			decideAction(tiles[playerTile.getXPos()][playerTile.getYPos() - 1]);
			player.setName("player"+playerNr+"North");
		}
		else if(key.equals("down")){
			decideAction(tiles[playerTile.getXPos()][playerTile.getYPos() + 1]);
			player.setName("player"+playerNr+"South");
		}
	}
	
	/**
	 * Getter for the current enemy that the player is interacting with.
	 * 
	 * @return The current enemy as an Enemy object.
	 */
	public Enemy getCurrentEnemy(){
		return currentEnemy;
	}
	
	/**
	 * Getter for the tiles array.
	 * 
	 * @return The tiles array.
	 */
	public Tile[][] getTiles(){
		return tiles;
	}
	
	/**
	 * Getter for the player character.
	 * 
	 * @return The player as a Player object.
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * Opens the map framework file that matches the map choice in the Choose Map menu.  
	 */
	public void openFile(){
		try{
			m =  new Scanner(new File("./res/map_"+mapNr+".txt"));
		}catch(Exception e){
			System.out.println("Error: Map failed to load");
		}
	}

	/**
	 * Reads the opened map file and creates the logical two-dimensional
	 * record of which static objects exist on each tile.
	 */
	public void readFile(){
		char[] c;
		while(m.hasNext()){
			for(int y=0; y<MAP_SIZE; y++){
				c = m.next().toCharArray();
				for(int x=0; x<MAP_SIZE; x++){
					if(c[x] == 'W'){
						tiles[x][y] = new Tile(x, y, "wall", true, true);
					}
					else if(c[x] == '.'){
						tiles[x][y] = new Tile(x, y, "grass", false, true);
					}
					else if(c[x] == '-'){
						tiles[x][y] = new Tile(x, y, "floor", false, true);
					}
					else if(c[x] == 'D'){
						tiles[x][y] = new Tile(x, y, "doorClosed", true, true);
						tiles[x][y].setDoor();
						doorTile = tiles[x][y];
					}
				}
			}
		}
	}

	/**
	 * Closes the file.
	 */
	public void closeFile(){
		m.close();
	}

	/**
	 * Gives visibility to each tile in a "diamond" shape around the player.
	 * This will remove the darkness layer from those tiles that is initially
	 * painted over the whole map.
	 */
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

		try{
			tiles[x+2][y].setDarkness(false);
			tiles[x][y+2].setDarkness(false);
			tiles[x-2][y].setDarkness(false);
			tiles[x][y-2].setDarkness(false);
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("NOT discovering extra tiles");
		}
	}

	/**
	 * Decides which action to perform depending on which interactive object exists on the next tile
	 * the player wants to move to. If no interactive object exists there, attempt to move the
	 * player to the tile.
	 * 
	 * @param nextTile The tile that the player is trying to move to.
	 */
	public void decideAction(Tile nextTile){
		if(nextTile.containsEnemy()){
			fight(nextTile);
			currentEnemy = (Enemy)nextTile.getInterObj();
		}
		else if(nextTile.containsItem()){
			pickUpItem((Item)nextTile.getInterObj());
			removeItem(nextTile);
		}
		else if(nextTile.containsDoor() && doorOpen){
			finishGame();
		}
		else if(nextTile.isEmpty()){
			movePlayerTo(nextTile);
			discoverDarkness();
		}
	}

	/**
	 * This function is called when an enemy exists on the next tile. The player will fight
	 * with the enemy and their stats will be used to reduce HP etc. A random number generator
	 * is used to play different punching sounds.
	 * 
	 * Here it's also checked if either the enemy and player have zero or negative HP, which means it's
	 * being killed and the appropriate actions are taken.
	 * 
	 * Every time an enemy is killed, we check if all the enemies are dead with a function call and
	 * if the function returns true, the variable that keeps track of this is also set to true and
	 * the "open door" sound is played. 
	 * 
	 * @param nextTile The tile that the player is trying to move to.
	 */
	public void fight(Tile nextTile){
		System.out.println("fighting");
		player.exchangeHitsWithEnemy(nextTile);
		if((nextTile.getInterObj().getHp()) <= 0 ){					//check enemy Hp, remove if <= 0.
			player.addExp(((Enemy)nextTile.getInterObj()).getExpReward()); 	//player get experience,  enemylevel * 10
			nextTile.setEnemy(null);
			nextTile.setBlood(true);
			currentEnemy = null;
			doorOpen = checkIfAllEnemiesAreDead();					//set to true if all enemies are dead.
			if(doorOpen){
				SoundManager.playSound("dooropen.wav");
			}else{
				SoundManager.playSound("enemydeath.wav");	
			}
		}else{
			int randomNum = rand.nextInt((5-1) + 1) + 1;
			SoundManager.playSound("fight_" + randomNum + ".wav");
		}

		if(player.getHp() <= 0) {
			playerDeath();
		}
	}

	/**
	 * This function is called when the player gets zero or negative HP.
	 * A "boo" sound is played to indicate this and a dialog window pops up
	 * informing the user of what happened. In this dialog there is a button to
	 * press which brings you back to the "Choose Map" menu.
	 * 
	 * No player progress will be saved upon death.
	 */
	private void playerDeath(){
		SoundManager.playSound("boo.wav");
		System.out.println("Player is dead");
		playerTile.setBlood(true);
		playerTile.setPlayer(null);

		Object[] options = {"Choose New Map"};
		int clicked = JOptionPane.showOptionDialog(null,
				"Oh dear, you have died!","Game Over",
				JOptionPane.PLAIN_MESSAGE,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				options,
				options[0]);

		if(clicked == JOptionPane.OK_OPTION) {
			GameFrame.hideMapPanel(); 
			GameFrame.hideStatusPanel();
			GameFrame.showStartMenuPanel();
		}
	}
	
	private void finishGame(){
		SoundManager.playSound("applause.wav");
		System.out.println("Game finished! Back to choose map menu.");
		Object[] options = {"Choose New Map"};
		
		save(playerNr);
		
		int clicked = JOptionPane.showOptionDialog(null,
				"You have completed the level!\nPlayer is saved.\n","Level Complete",
				JOptionPane.PLAIN_MESSAGE,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				options,
				options[0]);
		
		if(clicked == JOptionPane.OK_OPTION) {
			GameFrame.hideMapPanel();   
			GameFrame.hideStatusPanel();
			GameFrame.showStartMenuPanel();
		}
	}

	public void pickUpItem(Item item){
		System.out.println("pickUpItem()");
		
		player.updateStats(item);
	}

	public void removeItem(Tile nextTile){
		
		if(nextTile.getInterObj().getName()=="sword"){
			SoundManager.playSound("swordpickup.wav");
		}
		else if(nextTile.getInterObj().getName() == "shield"){
			SoundManager.playSound("shieldpickup.wav");
		}
		else if(nextTile.getInterObj().getName() == "potion"){
			SoundManager.playSound("drink.wav");
		}
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
	
	public void spawnObjectsInitiator(int difficulty){
		// Spawn(type, amount)
			switch(difficulty){
			case 1: spawnObjectsRandomly("enemyLv1", 4);
					spawnObjectsRandomly("enemyLv2", 3);
					spawnObjectsRandomly("enemyLv3", 3);
					spawnObjectsRandomly("itemSword", 1);
					spawnObjectsRandomly("itemShield", 1);
					spawnObjectsRandomly("itemPotion", 4);
					break;
			case 2: spawnObjectsRandomly("enemyLv1", 2);
					spawnObjectsRandomly("enemyLv2", 3);
					spawnObjectsRandomly("enemyLv3", 5);
					spawnObjectsRandomly("enemyLv4", 4);
					spawnObjectsRandomly("itemSword", 1);
					spawnObjectsRandomly("itemShield", 1);
					spawnObjectsRandomly("itemPotion", 4);
					break;
			case 3: spawnObjectsRandomly("enemyLv2", 2);
					spawnObjectsRandomly("enemyLv3", 5);
					spawnObjectsRandomly("enemyLv4", 4);
					spawnObjectsRandomly("enemyLv5", 5);
					spawnObjectsRandomly("itemSword", 1);
					spawnObjectsRandomly("itemShield", 1);
					spawnObjectsRandomly("itemPotion", 7);
					break;
			default: spawnObjectsRandomly("enemyLv1", 4);
					 spawnObjectsRandomly("enemyLv2", 3);
					 spawnObjectsRandomly("enemyLv3", 3);
					 spawnObjectsRandomly("itemSword", 1);
					 spawnObjectsRandomly("itemShield", 1);
				  	 spawnObjectsRandomly("itemPotion", 4);
				  	 break;
		}

	}

	public void spawnEnemy(int xPos, int yPos, String enemyType){

		if(tiles[xPos][yPos].getCollision()){
			System.out.println("Can not place Enemy on tile with collision");
			return;
		}

		if(enemyType == "enemyLv1"){
			Enemy enemy = new Enemy(1,1,10,10,"enemyLv1");	//(level,attack,hp,name)
			tiles[xPos][yPos].setEnemy(enemy);
		}

		if(enemyType == "enemyLv2"){ 
			Enemy enemy = new Enemy(2,2,20,20,"enemyLv2");  
			tiles[xPos][yPos].setEnemy(enemy);
		}
		
		if(enemyType == "enemyLv3"){
			Enemy enemy = new Enemy(3,3,30,30,"enemyLv3");
			tiles[xPos][yPos].setEnemy(enemy);
		}
		
		if(enemyType == "enemyLv4"){
			Enemy enemy = new Enemy(4,4,40,40,"enemyLv4");
			tiles[xPos][yPos].setEnemy(enemy);
		}
		if(enemyType == "enemyLv5"){
			Enemy enemy = new Enemy(5,5,50,50,"enemyLv5");
			tiles[xPos][yPos].setEnemy(enemy);
		}
	}

	public void spawnItem(int xPos, int yPos, String itemType){
		//Item(level,attack,hp,name)
		
		if(tiles[xPos][yPos].getCollision()){
			System.out.println("Can not place Item on tile with collision");
			return;
		}

		if(itemType == "itemSword"){
			Item item = new Item(1,5,0,0,"sword");
			tiles[xPos][yPos].setItem(item);
		}

		if(itemType == "itemShield"){ 
			Item item = new Item(1,0,10,0,"shield");
			tiles[xPos][yPos].setItem(item);
		}

		if(itemType == "itemPotion"){
			Item item = new Item(0,0,0,7,"potion");
			tiles[xPos][yPos].setItem(item);
		}
	}

	public void spawnObjectsRandomly(String type, int amount) {

		int xValue = rand.nextInt(MAP_SIZE-1) + 1;
		int yValue = rand.nextInt(MAP_SIZE-1) + 1;

		if( type == "enemyLv1" || type == "enemyLv2" || type == "enemyLv3" || type == "enemyLv4" || type == "enemyLv5") {
			for(int i = 0;  i < amount; i++) {

				while(tiles[xValue][yValue].getCollision()) {
					xValue = rand.nextInt(MAP_SIZE-1) +1;
					yValue = rand.nextInt(MAP_SIZE-1) +1;
				}
				if(!(tiles[xValue][yValue] == tiles[1][1])){
					spawnEnemy(xValue, yValue, type);
				}
				xValue = rand.nextInt(MAP_SIZE-1) +1;
				yValue = rand.nextInt(MAP_SIZE-1) +1;
				//System.out.println(xValue);
			}
		}

		if(type == "itemSword" || type == "itemShield" || type == "itemPotion") {
			for(int i = 0;  i < amount; i++) {
				while(tiles[xValue][yValue].getCollision()) {
					xValue = rand.nextInt(MAP_SIZE-1) +1;
					yValue = rand.nextInt(MAP_SIZE-1) +1;
				}
				if(!(tiles[xValue][yValue] == tiles[1][1])){
					spawnItem(xValue, yValue, type);
				}
				xValue = rand.nextInt(MAP_SIZE-1) +1;
				yValue = rand.nextInt(MAP_SIZE-1) +1; // rand 
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

