
import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class InventoryPanel extends JPanel {
	public InventoryPanel(){
		// set the size of the inventoryPanel
		Dimension size = getPreferredSize();
	    size.width = 240;
	    setPreferredSize(size);
	    setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	    
	    //Set layout manager
	    setLayout(new GridBagLayout());
	  
	    //create swing component
	    
	    // 	Labels
	    JLabel label0 = new JLabel("InvPanel");
	    JLabel label1 = new JLabel("  Attack  ");
	    JLabel label2 = new JLabel("  Health  ");
	    // 	ProgressBar
	    JProgressBar attackBar = new JProgressBar(0,100);
	    attackBar.setValue(20);
	    JProgressBar healthBar = new JProgressBar(0,100);
	    healthBar.setValue(90);
	    //	Inventory buttons
	    JButton itemButton1 = new JButton("ib1");
	    JButton itemButton2 = new JButton("ib2");
	    JButton itemButton3 = new JButton("ib3");
	    JButton itemButton4 = new JButton("ib4");
	    JButton itemButton5 = new JButton("ib5");
	    JButton itemButton6 = new JButton("ib6");
	    
	    
	    //add swing components to content pane
	    
	    ///////////////////// GridBagConstraints //////////////////////
	    GridBagConstraints gc = new GridBagConstraints();
	    
	    /*	gridlayout:
	     * 
	     *  0  1  2
	     *  1
	     * 	2
	     * 	3
	     * 	4
	     */

	    // first row 
	    
	    gc.ipady = 20;	//scale thickness in Y axis
	    gc.weightx = 1;
        gc.weighty = 1;
	    
	    gc.gridx = 1;
        gc.gridy = 0;
        add(label0, gc);
        
        //second row
        gc.gridx = 0;
        gc.gridy = 1;
        add(label1, gc);
        
        //third row
        gc.gridx = 0;
        gc.gridy = 2;
        add(label2, gc);
        
        //second row - statusbar
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 1;
        gc.weighty = 1;
        
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 2; 
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(attackBar, gc);
        
        //third row - statusbar
        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 2;
        add(healthBar, gc);
       
        // item buttons
        gc.gridwidth = 1;
        gc.weightx	= 1;
        gc.weighty = 1;
        
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 3;
        add(itemButton1, gc);
        
        gc.gridx = 1;
        gc.gridy = 3;
        add(itemButton2, gc);
        
        gc.gridx = 2;
        gc.gridy = 3;
        add(itemButton3, gc);
        
        gc.gridx = 0;
        gc.gridy = 4;
        add(itemButton4, gc);
        
        gc.gridx = 1;
        gc.gridy = 4;
        add(itemButton5, gc);
        
        gc.gridx = 2;
        gc.gridy = 4;
        add(itemButton6, gc);
	    
	    ///////////////////////////////////////////////////////////////
	    
	    
	}
	
}
