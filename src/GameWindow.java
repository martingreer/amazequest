import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class GameWindow {
	
	private static final int WINDOW_SIZE_X = 1024;
	private static final int WINDOW_SIZE_Y = 768;
	private static String VERSION = "Version:" + " Alpha 13.37";
	
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
		
		//Sub-choice of Help menu
		JMenuItem versionMenuItem = new JMenuItem("Version", KeyEvent.VK_V);
		versionMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
		versionMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,VERSION);
			}
			
			
		});
		helpMenu.add(versionMenuItem);
		
		
		//Options menu
		
		JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		menuBar.add(optionsMenu);
		
		//Sub-choice of Options menu
		JMenuItem optionsMenuItem1 = new JMenuItem("MMMBop", KeyEvent.VK_M);
		optionsMenuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
		
		optionsMenu.add(optionsMenuItem1);
		
		
		//Finally add bar to top of frame
		mainwindow.setJMenuBar(menuBar);
	
	}
	
public static void createButtons(JFrame mainwindow){
		
		//PlayButton
		final JButton playButton = new JButton("Play!");
		playButton.setSize(new Dimension(170, 130));
		playButton.setFont(new Font("Arial", Font.ITALIC, 45));
		//playButton.setLayout(null);
		mainwindow.add(playButton);
		
		playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				playButton.setVisible(false);
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