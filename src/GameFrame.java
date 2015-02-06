
import java.awt.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private InventoryPanel inventoryPanel;
	public GameFrame (String title){
		super(title);
		
		//Set layout manager
		setLayout(new BorderLayout());
		
		//create swing component
		JPanel boardAreaPlaceholder = new JPanel();
		inventoryPanel = new InventoryPanel();
		
		//add swing components to content pane
		Container c = getContentPane();
		c.add(boardAreaPlaceholder, BorderLayout.CENTER);
		c.add(inventoryPanel, BorderLayout.SOUTH);
		
		// Add functionality
		//...
		
	}

}