import java.awt.Image;

import javax.swing.ImageIcon;


@SuppressWarnings("unused")
public class Player {

	public int HP;
	public int ATTACK;
	public int xPos, yPos;
	public static String name;
	private ImageResources res;
	private Image playerf, playerw, playere;
	
	
	public Player(int xStartPos, int yStartPos, int startATTACK, int startHP, ID id){
		res = new ImageResources();
		ImageIcon img = new ImageIcon(res.getPath("playerf"));
		playerf = img.getImage();
		img = new ImageIcon(res.getPath("playerw"));
		playerw = img.getImage();
		img = new ImageIcon(res.getPath("playere"));
		playere = img.getImage();
		
		// Assign starting parameters for player
		setxPos(xStartPos);
		setyPos(yStartPos);
		setHP(startHP);
		setATTACK(startATTACK);
	}
	
	public Image getPlayerImage() {
		// TODO: Add cases for playerw playere
		return playerf;
	}
	
	public void setPlayerImage(Image image) {
		
	}
	
	public int getHP() {
		return HP;
	}

	public void setHP(int HP) {
		this.HP = HP;
	}

	public int getATTACK() {
		return ATTACK;
	}

	public void setATTACK(int ATTACK) {
		this.ATTACK = ATTACK;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public void move(String direction){
		switch (direction) {
			case "NORTH": setyPos(yPos++);
			case "SOUTH": setyPos(yPos--);
			case "WEST": setxPos(xPos--);
			case "EAST": setxPos(xPos++);
		}
	}
	
	/*
	if(keycode == KeyEvent.VK_UP){
		if(!map.getMap(player.getxPos(),player.getyPos()-1).equals("W")){
			player.move("NORTH");
		}   
	}
	if(keycode == KeyEvent.VK_DOWN){
		if(!map.getMap(player.getxPos(),player.getyPos()+1).equals("W")){
			player.move("SOUTH");
		}   
	}
	if(keycode == KeyEvent.VK_LEFT){
		if(!map.getMap(player.getxPos()-1,player.getyPos()).equals("W")){
			player.move("WEST");
		}   
	}
	if(keycode == KeyEvent.VK_RIGHT){
		if(!map.getMap(player.getxPos()+1,player.getyPos()).equals("W")){
			player.move("EAST");
		}   
	}
	*/
	
	/*
	@Override
	public void keyPressed(KeyEvent e){
		System.out.println("Key pressed");
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: setyPos(yPos--);
			case KeyEvent.VK_DOWN: setyPos(yPos++);
			case KeyEvent.VK_LEFT: setyPos(xPos--);
			case KeyEvent.VK_RIGHT: setyPos(xPos++);
		}
	}
	*/
}
