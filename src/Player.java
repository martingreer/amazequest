import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


@SuppressWarnings("unused")
public class Player extends GameEngine{

	public int HP;
	public int ATTACK;
	public int xPos, yPos;
	public static String name;
	private ImageResources res;
	private Image playerf, playerw, playere;
	
	
	public Player(int xPos, int yPos, int ATTACK, int HEALTH, ID id){
		res = new ImageResources();
		ImageIcon img = new ImageIcon(res.getPath("playerf"));
		playerf = img.getImage();
		img = new ImageIcon(res.getPath("playerw"));
		playerw = img.getImage();
		img = new ImageIcon(res.getPath("playere"));
		playere = img.getImage();
		
	}
	
	public int getHP() {
		return HP;
	}

	public void setHEALTH(int HP) {
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
