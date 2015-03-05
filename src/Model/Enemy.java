package Model;
/**
 * This class is called in the constructor of GameLogic and is responsible of keeping track of all 
 * the enemy objects on the map.
 * @author Jonas Arvidsson
 * @version 2015-03-05
 */

public class Enemy extends InteractiveObject {

	/**
	 * The amount of experience that an enemy object will give.
	 */
	private int expReward;
	/**
	 * 
	 * @param level	The level of the enemy
	 * @param attack The attack of the enemy
	 * @param maxHp The max health of the enemy	
	 * @param hp The health of the enemy
	 * @param name The name of the enemy
	 * @param exp The experience of the enemy
	 */
	public Enemy(int level, int attack, int maxHp, int hp, String name) {
		
		super(level, attack, maxHp, hp, name);
		expReward = level * 10;
	}
	/**
	 * Getter function for the enemy objects experience.
	 * Is called when an enemy is killed to give experience to the player.
	 * @return expReward. The amount of experience that an enemy hold as an int.
	 */
	public int getExpReward(){
		return expReward;
	}

}
