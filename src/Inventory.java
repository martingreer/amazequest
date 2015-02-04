
// package //


import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;




public class Inventory extends JFrame {
	
	
	  private ArrayList<String> itemStack;      // String ska vara " item " sen !!! bara för test

	 


	
	public Inventory() {
		
	
 
		
		itemStack = new ArrayList<String>(); 
		createInventory();
		
	}
	
	
	public  void createInventory() {
		
		
		// test to setup inventory 
		// future - images with items
		
		setTitle("Inventory");
		
		JFrame frame = new JFrame(); 
		
	
		
	    Container inventoryPan = getContentPane(); 
	    
		inventoryPan.setPreferredSize(new Dimension(400,300));
		
		inventoryPan.setLayout(new GridLayout(2,3,3,3));
		
		
		
		for(int i=0; i<6; i++) {
			
			
			
			JButton itemButton = new JButton("" );
			
			
			inventoryPan.add(itemButton);
			
			itemButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					task(e);    // do something with button
					
					
				}
					
					});
			
			
		}
		
		pack();
		setVisible(true);
		
		
	}
	
	public void addItem(String item) {            // change to Item item
		
	
		itemStack.add(item); 
		
	}
	
	public void removeItem(String item) {             // change to Item item
		
		
		// iterator ??
		
		itemStack.remove(item);
		
		
	}
	public void task(ActionEvent e) {                  // testing at the moment
		
		  int i = 1; 
		
		 JButton current = (JButton)e.getSource();
		 if(current.getText().equals("1")){
			 
			 i++;
			 
			 
		}
		 
		 current.setText("" + i);
		 
		 
		
	}
	
}

