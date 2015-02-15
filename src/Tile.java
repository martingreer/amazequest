
public class Tile {
	int xPos,yPos;
	private String imgID;
	private boolean collision;
	private InteractiveObject interactiveObject;
	
	public Tile(int xPos, int yPos, String imgID, boolean collision){
		this.xPos = xPos;
		this.yPos = yPos;
		this.imgID = imgID;
		this.collision = collision;
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
	
	public InteractiveObject getInterObj(){
		return interactiveObject;
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
}
