import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class StartmenuPanel extends JPanel {

	public JPanel mainMenu;

	JButton playButton;
	JButton settingsButton;
	JButton helpButton;
	JButton backButton;

	public StartmenuPanel(){

		mainMenu= new JPanel();
		mainMenu.setLayout(new GridBagLayout());

		playButton = new JButton("Play");
		settingsButton = new JButton("Settings");
		helpButton = new JButton("Help");
		backButton = new JButton("Back"); //backbutton

		GridBagConstraints g = new GridBagConstraints();

		g.weightx = 1;
		g.weighty = 1;

		g.gridx = 0;
		g.gridy = 0;
		add(playButton, g);

		g.gridx = 0;
		g.gridy = 1;
		add(settingsButton, g);

		g.gridx = 0;
		g.gridy = 2;
		add(helpButton, g);
		
		g.gridx = 0;
		g.gridy = 3;
		add(backButton,g);
	}


	public JButton getPlayButton(){
		return playButton;
	}

	public JButton getSettingsButton(){
		return settingsButton;
	}

	public JButton getHelpButton(){
		return helpButton;
	}
	public JButton getBackButton(){
		return backButton;
	}

}
