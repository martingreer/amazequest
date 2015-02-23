
public class Tile {
	int xPos,yPos;
	private String imgID;
	private boolean collision;
	private boolean darkness;
	private boolean blood;
	private InteractiveObject interactiveObject = null;
	private boolean door;
	
	public Tile(int xPos, int yPos, String imgID, boolean collision,boolean darkness){
		this.xPos = xPos;
		this.yPos = yPos;
		this.imgID = imgID;
		this.collision = collision;
		this.darkness = darkness;
		this.blood = false;
		door = false;
	}
	
	public String getImgID(){
		return imgID;
	}
	
	public void setPlayer(Player player){
		this.interactiveObject = player;
	}
	
	public void setEnemy(Enemy enemy){
		this.interactiveObject = enemy;
	}
	
	public void setItem(Item item){
		this.interactiveObject = item;
	}
	
	public void setDarkness(boolean value ){
		this.darkness = value;
	}
	public void setBlood(boolean value ){
		this.blood = value;
	}
	
	public InteractiveObject getInterObj(){
		return interactiveObject;
	}
	
	public boolean isEmpty(){
		if( interactiveObject != null){
			return false;
		}
		return true;
	}
	public boolean containsEnemy(){
		if( interactiveObject instanceof Enemy){
			return true;
		}
		return false;
	}
	public boolean containsItem(){
		if( interactiveObject instanceof Item){
			return true;
		}
		return false;
	}
	
	public boolean isDark(){
		if(darkness){
			return true;
		}
		return false;
	}
	
	public boolean isBlood(){
		if(blood){
			return true;
		}
		return false;
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}
	
	public boolean getCollision(){
		return collision;
	}
	
	public void setDoor(){
		door = true;
	}
	
	public void setImgID(String imgID){
		this.imgID = imgID;
	}
}
