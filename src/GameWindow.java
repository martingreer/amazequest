import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GameWindow {

	private static final int WINDOW_SIZE_X = 464;
	private static final int WINDOW_SIZE_Y = 660;
	private static String VERSION = "A Maze Quest " + "\n" + "Version:" + " Alpha 13.37" + "\n" + "© Jonas Brothers";
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
		mainwindow.setLocationRelativeTo(null);
		return mainwindow;
	}

	public static void createMenuBar(JFrame mainwindow){	
		//Create menu bar
		JMenuBar menuBar = new JMenuBar();


		//File menu
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);

		//Sub-choice of File menu
		JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		saveMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,"Game saved");
			}
		});
		fileMenu.add(saveMenuItem);

		JMenuItem loadMenuItem = new JMenuItem("Load", KeyEvent.VK_L);
		loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		loadMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,"Game loaded");
			}
		});
		fileMenu.add(loadMenuItem);

		

		//Help menu
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpMenu);

		

		JMenuItem versionMenuItem = new JMenuItem("Version", KeyEvent.VK_V);
		versionMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
		versionMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,VERSION);
			}

		});
		helpMenu.add(versionMenuItem);

		// Finally add bar to top of frame
		mainwindow.setJMenuBar(menuBar);

	}

	public static void createButtons(final JFrame mainwindow){   // final här ?? 

		JButton playButton;
		JButton settingsButton;
		JButton helpButton;
		JButton backButton;

		final JPanel startmenuPanel = new StartmenuPanel() ;     // final här ?? 12

		mainwindow.add(startmenuPanel,BorderLayout.CENTER);
		playButton = ((StartmenuPanel) startmenuPanel).getPlayButton();
		settingsButton= ((StartmenuPanel) startmenuPanel).getSettingsButton();
		helpButton = ((StartmenuPanel) startmenuPanel).getHelpButton();
		backButton = ((StartmenuPanel) startmenuPanel).getBackButton();
		backButton.setVisible(false);
	
		
		 playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startmenuPanel.setVisible(false);
				mainwindow.setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
				mainwindow.setResizable(true);
				mainwindow.setVisible(true);
				((GameFrame) mainwindow).showInventoryPanel();
				Map map = new Map();
				(new Thread(new GameEngine(map, mainwindow))).start();

			}
		}); 
	
		 
		helpButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			playButton.setVisible(false);
			settingsButton.setVisible(false);
			helpButton.setVisible(false);
			
			backButton.setVisible(true);
			
			backButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					playButton.setVisible(true);
					settingsButton.setVisible(true);
					helpButton.setVisible(true);
					backButton.setVisible(false);
				}
			});
	
			mainwindow.setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
			mainwindow.setResizable(true);
			mainwindow.setVisible(true);
	
			String helpInfo = "hej"; //help info string
	
		/*	JTextField helpText = new JTextField();
			helpText.setEditable(false);
			helpText.setText(helpInfo);
			mainwindow.add(helpText);
			 */
		
			
		}
	});
		
	settingsButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			playButton.setVisible(false);
			settingsButton.setVisible(false);
			helpButton.setVisible(false);
			backButton.setVisible(true);
			
			backButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					playButton.setVisible(true);
					settingsButton.setVisible(true);
					helpButton.setVisible(true);
					backButton.setVisible(false);
				}
			});
			
			mainwindow.setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
			mainwindow.setResizable(true);
			mainwindow.setVisible(true);
		}
	});
			
	} 
	public static void setGameFalse() {
		
		
		gameFrame.setVisible(false);
		
		}

}
