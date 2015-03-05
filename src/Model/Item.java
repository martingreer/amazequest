package Model;
/**
 * This class is respnsible for all the item objects in the game. 
 * Keeps track of all stats related to the item objects.
 * @author Jonas Arvidsson
 * @version 2015-03-05
 */
public class Item extends InteractiveObject{
	/**
	 * 
	 * @param level	The level of the item
	 * @param attack The attack of the item
	 * @param maxHp The max health of the item	
	 * @param hp The health of the item
	 * @param name The name of the item
	 * @param exp The experience of the item
	 */
	public Item(int level, int attack, int maxHp, int hp, String name) {
		super(level, attack, maxHp, hp, name);
	}
}
