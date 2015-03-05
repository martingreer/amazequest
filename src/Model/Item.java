package Model;
/**
 * This class is responsible for all the item objects in the game. 
 * Keeps track of all stats related to the item objects.  
 * @author Jonas Arvidsson
 * @version 2015-03-05
 */
public class Item extends InteractiveObject{
	/**
	 * 
	 * @param level The level of the item
	 * @param attack the attack of the item
	 * @param maxHp the max health points of the item
	 * @param hp the health points of the item
	 * @param name the name of the item
	 */
	public Item(int level, int attack, int maxHp, int hp, String name) {
		super(level, attack, maxHp, hp, name);
	}
}
