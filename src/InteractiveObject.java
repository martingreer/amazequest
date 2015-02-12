

public class InteractiveObject {

	private int level;
	private int attack;
	private int hp;
	private String name;
	private InteractiveObject object; 
	
	public InteractiveObject(int level, int attack, int hp, String name) {
		
		this.level = level;
		this.attack = attack;
		this.hp = hp;
		this.name = name;
		
		}
	
	// test methods // 
	
	public int returnHp() {
		
		return hp;
	}
	public int returnLevel() {
		
		return level;
		
	}
	public String returnName() {
		
		return name;
		
	}
	public int returnAttack(){
		
		return attack;
		
	}
	
}