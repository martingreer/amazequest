package View;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import Model.Enemy;
import Model.ImageResources;
import Model.Player;

/**
 * View-class that is created by the GameFrame.
 * The class is a JPanel that displays info about the player and the enemy thats currently being fought.
 * 
 * @author Johannes Uhr
 * @version 2015-03-03
 *
 */
@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
	
	/**
	 * Height of the JPanel.
	 */
	private static final int INVPANELHEIGHT = 150;
	
	/**
	 * Player's health.
	 */
	private static JProgressBar healthBar;
	
	/**
	 * Player's current exp.
	 */
	private static JProgressBar experienceBar;
	
	/**
	 * Current enemy's health.
	 */
	private static JProgressBar enemyHealthBar;
	
	/**
	 * Player's portrait as an ImageIcon.
	 */
	private static JLabel portraitLabel;
	
	/**
	 * Player's level
	 */
	private static JLabel playerLevelLabel;
	
	/**
	 * Player's attack-damage
	 */
	private static JLabel playerAttackLabel;
	
	/**
	 * Player's health in text
	 */
	private static JLabel playerHealthLabel;
	
	/**
	 * Enemy's level
	 */
	private static JLabel enemyLevelLabel;
	
	/**
	 * Enemy's attack-damage
	 */
	private static JLabel enemyAttackLabel;
	
	/**
	 * Enemy's health in text
	 */
	private static JLabel enemyHealthLabel;
	
	/**
	 * Enemy's portrait as an ImageIcon.
	 */
	private static JLabel enemyPortrait;
	
	/**
	 * Enemy's name
	 */
	private static JLabel enemyLabel;
	
	/**
	 * Resource object to retrieve images
	 */
	private static ImageResources res = new ImageResources();
	
	/**
	 * The constructor initialises the swing-objects and adds them to a gridbaglayout
	 */
	public StatusPanel(){
		Dimension size = getPreferredSize();
	    size.height = INVPANELHEIGHT;
	    setPreferredSize(size);
	    setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	    setLayout(new GridBagLayout());
	    
	    experienceBar = new JProgressBar();
	    experienceBar.setForeground(new Color(125, 0, 255));
	    healthBar = new JProgressBar();
	    healthBar.setForeground(Color.RED);
	    enemyHealthBar = new JProgressBar();
	    enemyHealthBar.setForeground(Color.RED);
	    
	    JPanel playerBarContainer = new JPanel();
	    playerBarContainer.setLayout(new GridLayout(3,1,0,2));
	    JLabel playerLabel = new JLabel("Player");
	    playerBarContainer.add(playerLabel);
	    playerBarContainer.add(healthBar);
	    playerBarContainer.add(experienceBar);

	    JPanel enemyBarContainer = new JPanel();
	    enemyBarContainer.setLayout(new GridLayout(3,1,0,2));    
	    enemyLabel = new JLabel("Enemy\n");
	    enemyBarContainer.add(enemyLabel);
	    enemyBarContainer.add(enemyHealthBar);
	    
	    JPanel playerPortraitContainer = new JPanel();	    
	    portraitLabel = new JLabel(res.getImgIcon("player1Portrait"));
	    playerPortraitContainer.add(portraitLabel);
	    
	    JPanel playerStatusContainer = new JPanel();
	    playerStatusContainer.setLayout(new GridLayout(3,1));	    
	    playerLevelLabel = new JLabel("");
	    playerStatusContainer.add(playerLevelLabel);
	    playerAttackLabel = new JLabel("");
	    playerStatusContainer.add(playerAttackLabel);
	    playerHealthLabel = new JLabel("");
	    playerStatusContainer.add(playerHealthLabel);
	    
	    JPanel enemyStatusContainer = new JPanel();
	    enemyStatusContainer.setLayout(new GridLayout(3,1));	  
	    enemyLevelLabel = new JLabel("");
	    enemyStatusContainer.add(enemyLevelLabel);
	    enemyAttackLabel = new JLabel("");
	    enemyStatusContainer.add(enemyAttackLabel);
	    enemyHealthLabel = new JLabel("");
	    enemyStatusContainer.add(enemyHealthLabel);
	    
	    JPanel enemyPortraitContainer = new JPanel();	    
	    enemyPortrait = new JLabel(res.getImgIcon(""));//Den här ska tas bort när vi kan kolla vilken fiende vi slåss mot
	    enemyPortraitContainer.add(enemyPortrait);
	    
	    GridBagConstraints gc = new GridBagConstraints();

	    gc.insets = new Insets(3,3,3,3);
	    gc.anchor = GridBagConstraints.WEST;
	    gc.weightx = 1;
	    gc.weighty = 1;
	    gc.gridwidth = 2;
        gc.gridx = 0;
        gc.gridy = 0;
        add(playerBarContainer, gc);
        
        gc.anchor = GridBagConstraints.EAST;
        gc.gridx = 2;
        gc.gridy = 0;
        add(enemyBarContainer, gc);
        
	    gc.gridwidth = 1;
	    gc.anchor = GridBagConstraints.WEST;
        gc.gridx = 0;
        gc.gridy = 1; 
        add(playerPortraitContainer, gc);
        
        gc.gridx = 1;
        gc.gridy = 1; 
        add(playerStatusContainer, gc);
        
        gc.anchor = GridBagConstraints.EAST;
        gc.gridx = 2;
        gc.gridy = 1; 
        add(enemyStatusContainer, gc);
        
        gc.gridx = 3;
        gc.gridy = 1; 
        add(enemyPortraitContainer, gc);

	}
	
	/**
	 * Update the swing-objects in the panel to values gathered from the current player and enemy.
	 * 
	 * @param player Reference to the current Player-object.
	 * @param enemy Reference to the enemy that is being fought at that time.
	 */
	public static void updatePanel(Player player, Enemy enemy){
		healthBar.setMaximum(player.getMaxHp());
		healthBar.setValue(player.getHp());
		experienceBar.setValue(player.getExp());
		playerAttackLabel.setText("Attack: "+player.getAttack());
		playerLevelLabel.setText("Level: "+player.getLevel());
		playerHealthLabel.setText("Health: "+player.getHp()+"/"+player.getMaxHp());
		portraitLabel.setIcon(res.getImgIcon("player"+player.getPlayerNr()+"Portrait"));
		if(enemy != null){
			enemyLabel.setVisible(true);
			enemyHealthBar.setVisible(true);
			enemyHealthBar.setMaximum(enemy.getMaxHp());
			enemyHealthBar.setValue(enemy.getHp());
			enemyAttackLabel.setText("Attack: "+enemy.getAttack());
			enemyLevelLabel.setText("Level: "+enemy.getLevel());
			enemyPortrait.setVisible(true);
			enemyPortrait.setIcon(res.getImgIcon(enemy.getName() + "Portrait"));
			enemyHealthLabel.setText("Health: "+enemy.getHp()+"/"+enemy.getMaxHp());
		}
		else{
			enemyLabel.setVisible(false);
			enemyHealthBar.setVisible(false);
			enemyHealthLabel.setText("");
			enemyPortrait.setVisible(false);
			enemyLevelLabel.setText("");
			enemyAttackLabel.setText("");
			
		}
	}
}
