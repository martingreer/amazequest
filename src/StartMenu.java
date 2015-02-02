import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class StartMenu {
	public static void main(String[] args){
		JFrame mainwindow = createWindow();
		createMenuBar(mainwindow);
	}
	
	public static JFrame createWindow(){
		//Create window frame
		JFrame mainwindow = new JFrame("A Maze Quest");
		mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainwindow.setSize(1024, 768);
		mainwindow.setResizable(false);
		mainwindow.setVisible(true);
		
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
}