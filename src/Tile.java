
public class Tile {
	int xPos,yPos;
	private String imgID;
	private boolean collision;
	
	public Tile(int xPos, int yPos, String imgID, boolean collision){
		this.xPos = xPos;
		this.yPos = yPos;
		this.imgID = imgID;
		this.collision = collision;
	}
	
	public String getImgID(){
		return imgID;
	}
	
}
