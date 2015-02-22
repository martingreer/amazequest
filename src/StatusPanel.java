

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


@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
	private static final int INVPANELHEIGHT = 150;
	private JProgressBar healthBar;
	private JProgressBar experienceBar;
	private JProgressBar enemyHealthBar;
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
	    healthBar = new JProgressBar(0,100);
	    healthBar.setForeground(Color.RED);
	    healthBar.setValue(80);
	    enemyHealthBar = new JProgressBar(0,100);
	    enemyHealthBar.setForeground(Color.RED);
	    enemyHealthBar.setValue(20);
	    
	    JPanel playerBarContainer = new JPanel();
	    playerBarContainer.setLayout(new GridLayout(3,1));
	    JLabel playerLabel = new JLabel("Player");
	    playerBarContainer.add(playerLabel);
	    playerBarContainer.add(healthBar);
	    playerBarContainer.add(experienceBar);

	    JPanel enemyBarContainer = new JPanel();
	    enemyBarContainer.setLayout(new GridLayout(3,1));	    
	    JLabel enemyLabel = new JLabel("Enemy");
	    enemyBarContainer.add(enemyLabel);
	    enemyBarContainer.add(enemyHealthBar);
	    
	    JPanel playerPortraitContainer = new JPanel();	    
	    JLabel picLabel = new JLabel(res.getImgIcon("playerPortrait"));
	    playerPortraitContainer.add(picLabel);
	    
	    JPanel playerStatusContainer = new JPanel();
	    playerStatusContainer.setLayout(new GridLayout(2,1));	    
	    JLabel playerAttackLabel = new JLabel("Attack: 10");
	    playerStatusContainer.add(playerAttackLabel);
	    JLabel playerLevelLabel = new JLabel("Level: 2");
	    playerStatusContainer.add(playerLevelLabel);
	    
	    JPanel enemyStatusContainer = new JPanel();
	    enemyStatusContainer.setLayout(new GridLayout(2,1));	    
	    JLabel enemyAttackLabel = new JLabel("Attack: 8");
	    enemyStatusContainer.add(enemyAttackLabel);
	    JLabel enemyLevelLabel = new JLabel("Level: 3");
	    enemyStatusContainer.add(enemyLevelLabel);
	    
	    JPanel enemyPortraitContainer = new JPanel();	    
	    JLabel enemyPortrait = new JLabel(res.getImgIcon("enemyLv1"));
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
       
        gc.gridx = 2;
        gc.gridy = 0;
        add(enemyBarContainer, gc);
        
	    gc.gridwidth = 1;
        
        gc.gridx = 0;
        gc.gridy = 1; 
        add(playerPortraitContainer, gc);
        
        gc.gridx = 1;
        gc.gridy = 1; 
        add(playerStatusContainer, gc);
        
        gc.gridx = 2;
        gc.gridy = 1; 
        add(enemyStatusContainer, gc);
        
        gc.gridx = 3;
        gc.gridy = 1; 
        add(enemyPortraitContainer, gc);
        
	    
	}
	
}
