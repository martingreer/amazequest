
import java.awt.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	private StatusPanel statusPanel;
	public GameFrame (String title){
		super(title);
		
		//Set layout manager
		setLayout(new BorderLayout());
		
		//create swing component
		JPanel boardAreaPlaceholder = new JPanel();
		statusPanel = new StatusPanel();
		
		//add swing components to content pane
		Container c = getContentPane();
		c.add(boardAreaPlaceholder, BorderLayout.CENTER);
		c.add(statusPanel, BorderLayout.SOUTH);
		// Add functionality
		//...
		hideInventoryPanel();
	}
	
	public void showInventoryPanel(){
		statusPanel.setVisible(true);
	
	}
	
	public void hideInventoryPanel(){
		statusPanel.setVisible(false);
		
	}



}
