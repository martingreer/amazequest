
// package //


import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;




public class Inventory extends JFrame {
	
	
	  private ArrayList<Item> itemStack;      // String ska vara " item " sen !!! bara för test

	 


	
	public Inventory() {
		
	
 
		
		itemStack = new ArrayList<Item>(); 
		//createInventory();
		
	}
	public void addItem(Item item) {            // change to Item item
		
		
		itemStack.add(item); 
		
	}
	
	public void removeItem(Item item) {             // change to Item item
		
		
		// iterator ??
		
		itemStack.remove(item);
		
		
	}
	
	public int showSize(){
		
	return itemStack.size();
	
		
	}

	
	
}

