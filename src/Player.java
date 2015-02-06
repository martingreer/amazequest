import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


@SuppressWarnings("unused")
public class Player extends GameEngine{

	public int HEALTH;
	public int ATTACK;
	public int xPos, yPos;
	public static String name;
	//image?
	
	public Player(int xPos, int yPos, int ATTACK, int HEALTH, ID id){
		
	}
	
	public int getHEALTH() {
		return HEALTH;
	}

	public void setHEALTH(int HEALTH) {
		this.HEALTH = HEALTH;
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

	public void move(){
		
	}
	
	/*
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_W){
			setyPos(yPos - 1);
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			setyPos(yPos + 1);
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			setyPos(xPos + 1);
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			setyPos(xPos - 1);
		}
	}
	 */
}
