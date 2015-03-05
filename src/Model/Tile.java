package Model;

/**
 * 	This class is created in GameLogic and is responsible for its position, image ID, if there is a collision
 * 	and also if there is darkness on the map. 
 *  Every tile is painted with an image in the MapPanel.
 * 
 * @author David Fogelberg
 * @version 2015-03-05
 */

public class Tile {
	int xPos,yPos;
	private String imgID;
	private boolean collision;
	private boolean darkness;
	private boolean blood;
	private InteractiveObject interactiveObject = null;
	private boolean door;
	
	
	/**
	 * 
	 * @param xpos The x position of the tile.
	 * @param ypos The y position of the tile.
	 * @param imgID The image ID that will be placed on the tile.
	 * @param collision If there is a collision on the tile. 
	 * @param darkness  If there is darkness on the tile.
	 */

	public Tile(int xPos, int yPos, String imgID, boolean collision,boolean darkness){
		this.xPos = xPos;
		this.yPos = yPos;
		this.imgID = imgID;
		this.collision = collision;
		this.darkness = darkness;
		this.blood = false;
		this.door = false;
	}
	
	/**
	 * @return The image ID of the tile.
	 */

	public String getImgID(){
		return imgID;
	}
	/**
	 * @param The current player.
	 * Method sets this specific tile to the current player.
	 */
	public void setPlayer(Player player){
		this.interactiveObject = player;
	}
	/**
	 * @param The current enemy.
	 * Method sets this specific tile to the current enemy.
	 */
	public void setEnemy(Enemy enemy){
		this.interactiveObject = enemy;
	}
	/**
	 * @param The current item.
	 * Method sets this specific tile to the current item.
	 */
	public void setItem(Item item){
		this.interactiveObject = item;
	}
	/**
	 * @param The current darkness (true or false). 
	 * Method sets this specific tile to the current darkness.
	 * If darkness is true then specific tile will be painted black. 
	 */
	public void setDarkness(boolean value ){
		this.darkness = value;
	}
	/**
	 * @param The current blood (true or false). 
	 * Method sets this specific tile to the current blood.
	 * If blood is true then specific tile will be painted in blood.
	 */
	public void setBlood(boolean value ){
		this.blood = value;
	}
	/**
	 * @return the specific interactive object, it is either player, enemy or item.
	 */
	public InteractiveObject getInterObj(){
		return interactiveObject;
	}
	/**
	 * Method checks if the tile contains an interactive object.
	 * @return true or false.
	 */
	public boolean isEmpty(){
		if( interactiveObject != null){
			return false;
		}
		return true;
	}
	/**
	 * Method checks if the tile contains an enemy object.
	 * @return true or false.
	 */
	public boolean containsEnemy(){
		if( interactiveObject instanceof Enemy){
			return true;
		}
		return false;
	}
	/**
	 * Method checks if the tile contains an item object.
	 * @return true or false.
	 */
	public boolean containsItem(){
		if( interactiveObject instanceof Item){
			return true;
		}
		return false;
	}
	/**
	 * Method checks if the tile contains an door object.
	 * @return true or false
	 */
	public boolean containsDoor(){
		return door;
	}
	/**
	 * Method checks if the tile contains darkness.
	 * @return true or false.
	 */
	public boolean isDark(){
		if(darkness){
			return true;
		}
		return false;
	}
	/**
	 * Method checks if the tile contains blood.
	 * @return true or false.
	 */
	public boolean isBlood(){
		if(blood){
			return true;
		}
		return false;
	}
	/**
	 * @return x positon of tile.
	 */
	public int getXPos(){
		return xPos;
	}
	/**
	 * @return y positon of tile.
	 */
	public int getYPos(){
		return yPos;
	}
	/**
	 * @return true if there is a collision otherwise false 
	 */
	public boolean getCollision(){
		return collision;
	}
	/**
	 * Method sets the door object to true.
	 */
	public void setDoor(){
		door = true;
	}
	/**
	 * @param Image ID 
	 * Method changes the current image ID to the new image ID.
	 */
	public void setImgID(String imgID){
		this.imgID = imgID;
	}
}
