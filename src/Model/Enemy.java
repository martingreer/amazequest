package Model;


public class Enemy extends InteractiveObject {

	private int expReward;
	public Enemy(int level, int attack, int maxHp, int hp, String name) {
		
		super(level, attack, maxHp, hp, name);
		expReward = level * 10;
	}
	
	public int getExpReward(){
		return expReward;
	}

}
