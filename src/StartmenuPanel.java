import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class StartmenuPanel extends JPanel {

	public JPanel mainMenu;

	JButton playButton;
	JButton settingsButton;
	JButton helpButton;

	public StartmenuPanel(){

		mainMenu= new JPanel();
		mainMenu.setLayout(new GridBagLayout());

		playButton = new JButton("play");
		settingsButton = new JButton("settings");
		helpButton = new JButton("help");

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

}
