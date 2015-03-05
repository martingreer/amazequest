package Model;
import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;
import java.util.ArrayList;

/**
 *	This class is initiated in the constructor of GameLogic and is responsible of keeping 
 *	track of all player related stats. It implements the serialized interface so that the 
 *	state can be saved permanently on a local file. 
 *
 *	The class can interact with Enemy and Item objects.
 * 
 * 
 * @author Henrik Numé
 * @version 2015-03-05
 *
 */
public class Player extends InteractiveObject implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6234382920123790369L;
	/**
	 * Keeps track of the experience earned by the player.
	 */
	private int exp;
	
	/**
	 * Constructor that will create a new Player object with the given values as start values.
	 * 
	 * @param level,	the level of the player
	 * @param attack,	the attack of the player
	 * @param maxHp,	the max health of the player	
	 * @param hp,		the health of the player
	 * @param name,		the name of the player
	 * @param exp,		the experience of the player
	 */
	public Player(int level, int attack, int maxHp, int hp, String name, int exp) {
		
		super(level, attack, maxHp, hp, name);
		this.exp = exp;
		}
	
	/**
	 * Getter for the players experience
	 * 
	 * @return The players experience as an int.
	 */
	public int getExp() {
		
		return exp;
		
	}
	/**
	 * Getter for the players experience
	 * 
	 * @return The players experience as an int.
	 */
	public void setExp(int value){
			
			exp = value;
	}
	
	public void addExp(int change){
		
		exp += change;
		if(exp >= 100){			//how much exp is needed to level up
			this.addLevel(1);
			SoundManager.playSound("levelup1.wav");
			exp -= 100;
			this.addAttack(2);
			this.addMaxHp(2);
			this.addHp(3); //level up bonus hp
		}
	}
	
	public void healToFull(){
		setHp(getMaxHp());
	}
	
	public int getPlayerNr(){
		if(getName().contains("2")){
			return 2;
		}
		else
			return 1;
	}
	public void updateStats(Item item){
		
		this.addHp(item.getHp());
		this.addMaxHp(item.getMaxHp());
		this.addAttack(item.getAttack());
		if(getHp()>getMaxHp()){
			healToFull();
		}
	}
	
	public void exchangeHitsWithEnemy(Tile nextTile){
		
		Enemy enemy = (Enemy)nextTile.getInterObj();
		
		enemy.addHp(-getAttack());
		if(enemy.getHp() > 0){
			addHp(-enemy.getAttack());
		}
		System.out.println("player HP:" + getHp());
		System.out.println("enemy HP:" + enemy.getHp());
	}
}
