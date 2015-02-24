import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

import java.util.ArrayList;

public class Player extends InteractiveObject implements Serializable {
	
	private static final long serialVersionUID = -6234382920123790369L;
	private int exp;                  // experience 
	
	public Player(int level, int attack, int maxHp, int hp, String name, int exp) {
		
		super(level, attack, maxHp, hp, name);
		this.exp = exp;
		}
	
	public int getExp() {
		
		return exp;
		
	}
	
	public void setExp(int change){
		
		exp = exp + change;
		if(exp >= 100){			//how much exp is needed to level up
			this.setLevel(1);
			exp = exp - 100;
			this.setAttack(2);
			this.setMaxHp(2);
			this.setHp(5); //level up bonus hp
		}
	}
	
	
	public void updateStats(Item item){
		
		this.setHp(item.getHp());
		this.setMaxHp(item.getMaxHp());
		this.setAttack(item.getAttack());
		if(getHp()>getMaxHp()){
			setHp(getMaxHp()-getHp());
		}
	}
	
	public void exchangeHitsWithEnemy(Tile nextTile){
		
		InteractiveObject enemy = nextTile.getInterObj();
		
		enemy.setHp(-getAttack());
		if(enemy.getHp() > 0){
			setHp(-enemy.getAttack());
		}
		System.out.println("player HP:" + getHp());
		System.out.println("enemy HP:" + enemy.getHp());
	}
}
