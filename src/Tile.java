
public class Tile {
	int xPos,yPos;
	private String imgID;
	private boolean collision;
	private Player player;
	
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
		this.player = player;
	}
	
	public Player getPlayer(){
		return player;
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
