
// package //


import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;




public class Inventory extends JFrame {
	
	
	  private ArrayList<String> itemStack;      // String ska vara " item " sen !!! bara för test

	 


	
	public Inventory() {
		
	
 
		
		itemStack = new ArrayList<String>(); 
		//createInventory();
		
	}
	public void addItem(String item) {            // change to Item item
		
		
		itemStack.add(item); 
		
	}
	
	public void removeItem(String item) {             // change to Item item
		
		
		// iterator ??
		
		itemStack.remove(item);
		
		
	}
	
	
}

