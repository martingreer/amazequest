package Model;
import java.io.Serializable;



public class InteractiveObject implements Serializable {

	private static final long serialVersionUID = 7164610381028851449L;
	private int level;
	private int attack;
	private int hp;
	private String name;
	private int maxHp;
	
	public InteractiveObject(int level, int attack, int maxHp, int hp, String name) {
		
		this.level = level;
		this.attack = attack;
		this.maxHp = hp;
		this.hp = hp;
		this.name = name;
		this.maxHp = maxHp;
		
		}
	
	// test methods // 
	
	public int getHp() {
		
		return hp;
	}
	
	public int getMaxHp() {
		
		return maxHp;
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
	
	public void setMaxHp(int change) {
		
		maxHp = maxHp + change;
	}
		
	public void setAttack(int change) {
		
		attack = attack + change;
	}
	
	public void setLevel(int change) {
		
		level = level + change;
	}
	
}