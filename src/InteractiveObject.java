

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
	
	public int getHp() {
		
		return hp;
	}
	public int getLevel() {
		
		return level;
		
	}
	public String getName() {
		
		return name;
		
	}
	public int getAttack(){
		
		return attack;
		
	}
	public void setName(String name) {
			
			this.name = name;
			
	}
	public void setHp(int change) {
		
		hp = hp + change;
	}
		
	public void setAttack(int change) {
		
		attack = attack + change;
	}
	
	public void setLevel(int change) {
		
		level = level + change;
	}
	
}