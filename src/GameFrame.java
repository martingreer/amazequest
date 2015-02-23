
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
		hideStatusPanel();
	}
	
	public void showStatusPanel(){
		statusPanel.setVisible(true);
	}
	
	public void hideStatusPanel(){
		statusPanel.setVisible(false);
		
	}



}
