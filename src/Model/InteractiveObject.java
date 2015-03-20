package Model;
import java.io.Serializable;

/**
 * 	This class is a superclass for item, enemy and player. It is initiated when one of these classes are initiated.
 *  It also keeps track of all common methods for these classes and it implements Serializable so that all current stats can 
 *  be saved. 
 * 
 * @author David Fogelberg
 * @version 2015-03-05
 */


public class InteractiveObject implements Serializable {

	private static final long serialVersionUID = 7164610381028851449L;
	private int level;
	private int attack;
	private int hp;
	private String name;
	private int maxHp;
	
	/**
	 * 
	 * @param level	The level of the enemy,player or item.
	 * @param attack The attack of the enemy, player or granted by item.
	 * @param maxHp The max health of the enemy, player or granted by item.
	 * @param hp The health of the enemy, player or granted by item.
	 * @param name The name of the enemy, player or item.
	  */
	
	public InteractiveObject(int level, int attack, int maxHp, int hp, String name) {
		
		this.level = level;
		this.attack = attack;
		this.maxHp = hp;
		this.hp = hp;
		this.name = name;
		this.maxHp = maxHp;
		
		}
	
	/**
	 * @return The health of player, enemy or item.
	 */
	public int getHp() {
		
		return hp;
	}
	/**
	 * @return The max health of player, enemy, or item.
	 */
	public int getMaxHp() {
		
		return maxHp;
	}
	/**
	 * @return The level of player, enemy, or item.
	 */
	public int getLevel() {
		
		return level;
		
	}
	/**
	 * @return The name of player, enemy, or item.
	 */
	public String getName() {
		
		return name;
		
	}
	/**
	 * @return The attack of player, enemy, or item.
	 */
	public int getAttack(){
		
		return attack;
		
	}
	/**
	 * @param name The new name of player, enemy or item.
	 * Method  Sets player, enemy or item to the new name.
	 */
	public void setName(String name) {
			
			this.name = name;
			
	}
	/**
	 * @param newHp The new health of player, enemy or item.
	 * Method Sets player, enemy or item to the new health.
	 */
	public void setHp(int newHp) {
		
		hp = newHp;
	}
	/**
	 * @param change The new health that changes the current health of player, enemy or item.
	 * Method Adds the new health to the current health of player, enemy or item. 
	 */
	public void addHp(int change) {
		
		hp = hp + change;
	}
	/**
	 * @param change The new max health that changes the current max health of player, enemy or item.
	 * Method Adds the new max health to the current max health of player, enemy or item. 
	 */
	public void addMaxHp(int change) {
		
		maxHp = maxHp + change;
	}
	/**
	 * @param change The new attack that changes the current attack of player, enemy or item.
	 * Method Adds the new attack to the current attack of player, enemy or item. 
	 */
	public void addAttack(int change) {
		
		attack = attack + change;
	}
	/**
	 * @param change The new level that changes the current level of player, enemy or item. 
	 * Method adds the new level to the current level of player, enemy or item. 
	 */
	public void addLevel(int change) {
		
		level = level + change;
	}
	
}