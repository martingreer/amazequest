
import java.awt.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private InventoryPanel inventoryPanel;
	public MainFrame (String title){
		super(title);
		
		//Set layout manager
		setLayout(new BorderLayout());
		
		//create swing component
		JTextArea boardAreaPlaceholder = new JTextArea();
		JButton button =new JButton("button");
		inventoryPanel = new InventoryPanel();
		
		//add swing components to content pane
		Container c = getContentPane();
		c.add(boardAreaPlaceholder, BorderLayout.CENTER);
		c.add(button, BorderLayout.SOUTH);
		c.add(inventoryPanel, BorderLayout.WEST);
		
		// Add functionality
		//...
		
	}

}