import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GameWindow {

	private static final int WINDOW_SIZE_X = 1024;
	private static final int WINDOW_SIZE_Y = 768;

	private static GameFrame gameFrame;

	public static void main(String[] args){
		gameFrame = createWindow();
		createMenuBar(gameFrame);
		createButtons(gameFrame);
		gameFrame.setVisible(true);
	}

	public static GameFrame createWindow(){
		//Create window frame
		GameFrame mainwindow = new GameFrame("A Maze Quest");
		mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainwindow.setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
		mainwindow.setResizable(true);
		return mainwindow;
	}

	public static void createMenuBar(JFrame mainwindow){	
		//Create menu bar
		JMenuBar menuBar = new JMenuBar();

		//Game menu
		JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic(KeyEvent.VK_G);
		gameMenu.getAccessibleContext().setAccessibleDescription("Description");
		menuBar.add(gameMenu);

		//Sub-choice of Game menu
		JMenuItem gameMenuItem1 = new JMenuItem("Blabla", KeyEvent.VK_B);
		gameMenuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		gameMenuItem1.getAccessibleContext().setAccessibleDescription("Weee!");
		gameMenu.add(gameMenuItem1);

		//Help menu
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.getAccessibleContext().setAccessibleDescription("Description Help");
		menuBar.add(helpMenu);

		//Sub-choice of Help menu
		JMenuItem helpMenuItem1 = new JMenuItem("Blabla", KeyEvent.VK_B);
		helpMenuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		helpMenuItem1.getAccessibleContext().setAccessibleDescription("Weee!");
		helpMenu.add(helpMenuItem1);

		//Finally add bar to top of frame
		mainwindow.setJMenuBar(menuBar);

	}

	public static void createButtons(JFrame mainwindow){
		
		JButton playButton;
		JButton settingsButton;
		JButton helpButton;

		JPanel startmenuPanel = new StartmenuPanel() ;

		mainwindow.add(startmenuPanel,BorderLayout.CENTER);
		playButton = ((StartmenuPanel) startmenuPanel).getPlayButton();
		settingsButton = ((StartmenuPanel) startmenuPanel).getSettingsButton();
		helpButton = ((StartmenuPanel) startmenuPanel).getHelpButton();

		playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startmenuPanel.setVisible(false);
				mainwindow.setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
				mainwindow.setResizable(true);
				mainwindow.setVisible(true);
				((GameFrame) mainwindow).showInventoryPanel();
				Board board = new Board();
				(new Thread(new GameEngine(board, mainwindow))).start();

			}
		});
		//Other buttons

	} 

}