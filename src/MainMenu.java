import java.awt.FlowLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainMenu extends JFrame {

	public MainMenu() {
		initUI();
	}
	
	public final void initUI(){
		setTitle("World of Swag");
		setSize(700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		MainMenu mainmenu = new MainMenu();
		mainmenu.setVisible(true);
	}
    
}