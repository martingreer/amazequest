import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Player extends InteractiveObject {
	
	private ArrayList<Item> items;     // items 
	private int exp;                  // experience 
	
	public Player(int level, int attack, int hp, String name, int exp) {
		
		super(level, attack, hp, name);
		
		items = new ArrayList<Item>();
		
		this.exp = exp;
		
		}
	
	public int returnExp() {
		
		return exp;
		
	}
/*
	public void move(String direction){
		switch (direction) {
			case "NORTH": setyPos(yPos++);
			case "SOUTH": setyPos(yPos--);
			case "WEST": setxPos(xPos--);
			case "EAST": setxPos(xPos++);
		}
	}
	*/
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
