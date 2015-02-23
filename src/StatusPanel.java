

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


@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
	private static final int INVPANELHEIGHT = 150;
	private JProgressBar healthBar;
	private JProgressBar experienceBar;
	private JProgressBar enemyHealthBar;
	private JLabel portraitLabel;
	private JLabel playerAttackLabel;
	private JLabel playerLevelLabel;
	private JLabel enemyAttackLabel;
	private JLabel enemyLevelLabel;
	private JLabel enemyPortrait;
	
	private ImageResources res = new ImageResources();
	
	public StatusPanel(){
		Dimension size = getPreferredSize();
	    size.height = INVPANELHEIGHT;
	    setPreferredSize(size);
	    setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	    
	    setLayout(new GridBagLayout());
	  
	    experienceBar = new JProgressBar(0,100);
	    experienceBar.setForeground(new Color(125, 0, 255));
	    experienceBar.setValue(30);
	    healthBar = new JProgressBar();
	    healthBar.setForeground(Color.RED);
	    enemyHealthBar = new JProgressBar(0,100);
	    enemyHealthBar.setForeground(Color.RED);
	    enemyHealthBar.setValue(20);
	    
	    JPanel playerBarContainer = new JPanel();
	    playerBarContainer.setLayout(new GridLayout(3,1,0,2));
	    JLabel playerLabel = new JLabel("Player");
	    playerBarContainer.add(playerLabel);
	    playerBarContainer.add(healthBar);
	    playerBarContainer.add(experienceBar);

	    JPanel enemyBarContainer = new JPanel();
	    enemyBarContainer.setLayout(new GridLayout(3,1,0,2));    
	    JLabel enemyLabel = new JLabel("Enemy");
	    enemyBarContainer.add(enemyLabel);
	    enemyBarContainer.add(enemyHealthBar);
	    
	    JPanel playerPortraitContainer = new JPanel();	    
	    portraitLabel = new JLabel(res.getImgIcon("playerPortrait"));
	    playerPortraitContainer.add(portraitLabel);
	    
	    JPanel playerStatusContainer = new JPanel();
	    playerStatusContainer.setLayout(new GridLayout(2,1));	    
	    playerAttackLabel = new JLabel("Attack: 10");
	    playerStatusContainer.add(playerAttackLabel);
	    playerLevelLabel = new JLabel("Level: 2");
	    playerStatusContainer.add(playerLevelLabel);
	    
	    JPanel enemyStatusContainer = new JPanel();
	    enemyStatusContainer.setLayout(new GridLayout(2,1));	    
	    enemyAttackLabel = new JLabel("Attack: 8");//Den här ska tas bort när vi kan kolla vilken fiende vi slåss mot
	    enemyStatusContainer.add(enemyAttackLabel);
	    enemyLevelLabel = new JLabel("Level: 3");//Den här ska tas bort när vi kan kolla vilken fiende vi slåss mot
	    enemyStatusContainer.add(enemyLevelLabel);
	    
	    JPanel enemyPortraitContainer = new JPanel();	    
	    enemyPortrait = new JLabel(res.getImgIcon("enemyLv1"));//Den här ska tas bort när vi kan kolla vilken fiende vi slåss mot
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
	

	public void updatePanel(Player player, Enemy enemy){
		healthBar.setMaximum(player.getMaxHp());
		healthBar.setValue(player.getHp());
	}
}
