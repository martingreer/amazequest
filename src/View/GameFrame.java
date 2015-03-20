package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;
import Model.ImageResources;

/**
 * Created by Main.
 * JFrame that is the core view-class for the game.
 * This class hold the 3 different panels of the game and shows/hides them based on the player's actions.
 * 
 * @author Johannes Uhr
 * @version 2015-03-03
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	/**
	 * Horizontal size of the frame.
	 */
	private static final int WINDOW_SIZE_X = 20*32+16;
	
	/**
	 * Vertical size of the frame.
	 */
	private static final int WINDOW_SIZE_Y = 20*32+208;
	
	/**
	 * Will be displayed when clicking Help->Version.
	 */
	private static String VERSION = "Amazequest " + "\n" + "Version:" + " 1.0" + "\n" + "Grupp 1 DAT055";
	
	/**
	 * Resource-object to retrieve images.
	 */
	private ImageResources res = new ImageResources();
	
	/**
	 * Menu bar of the game.
	 */
	private JMenuBar menuBar;
	
	/**
	 * Status panel shown at the bottom of the frame.
	 */
	private static StatusPanel statusPanel;
	
	/**
	 * Start menu panel, never shown together with map panel and status panel.
	 */
	private static JPanel startMenuPanel;
	
	/**
	 * Map panel, never shown together with start menu panel.
	 */
	private static MapPanel mapPanel;
	
	/**
	 * These labels will change if user is asked to choose player or maps.
	 */
	private JLabel labelIcon1, labelIcon2, labelIcon3;
	
	/**
	 * Asks the user to either choose player or map.
	 */
	private JLabel choiceLabel;
	
	/**
	 * These buttons will change if user is asked to choose player or maps.
	 */
	private JButton button1, button2, button3;
	
	/**
	 * Used to reset the player states to start values.
	 */
	private JButton resetButton1, resetButton2;
	
	/**
	 * Choices for player 1 or 2 and map 1, 2 or 3.
	 */
	private int playerChoice, mapChoice;
	
	/**
	 * Used to set appropriate layout for the start menu panel.
	 */
	private GridBagConstraints gc;
	
	/**
	 * Sets up the layout for the game. Initialises the panels with their labels and buttons. 
	 * Hides the map panel and status panel while showing the start menu panel.
	 * @param title Game title at the top of the window.
	 */
	public GameFrame (String title){
		super(title);	
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
		setResizable(true);
		setLocationRelativeTo(null);
		statusPanel = new StatusPanel();
		mapPanel = new MapPanel();
		add(mapPanel);
		mapPanel.setVisible(false);
		startMenuPanel = new JPanel(new GridBagLayout());
		gc = new GridBagConstraints();
		add(startMenuPanel,BorderLayout.CENTER);
		add(statusPanel, BorderLayout.SOUTH);
		hideStatusPanel();
		addMenu();
		createLabels();
		createButtons();
	}
	
	/**
	 * Sets up the buttons and labels to display the state of the start menu panel where the user is asked to choose a player.
	 */
	public void playerChoiceDisplay(){
		button1.setText("Player 1");
		button2.setText("Player 2");
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridheight = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.SOUTH;
		startMenuPanel.add(button1, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.NORTH;
        startMenuPanel.add(resetButton1, gc);
        resetButton1.setVisible(true);
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.SOUTH;
        startMenuPanel.add(button2, gc);
        gc.gridx = 1;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.NORTH;
		startMenuPanel.add(resetButton2, gc);
		resetButton2.setVisible(true);
        gc.gridx = 1;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.NORTH;
        
		startMenuPanel.add(button3, gc);
		button3.setVisible(false);
		
		labelIcon1.setIcon(res.getImgIcon("player1Big"));
		labelIcon2.setIcon(res.getImgIcon("player2Big"));
		labelIcon3.setIcon(null);
		choiceLabel.setText("Choose player");
		
		gc.anchor = GridBagConstraints.CENTER;
	    gc.gridwidth = 2;
	    gc.gridheight = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        startMenuPanel.add(choiceLabel, gc);
        gc.anchor = GridBagConstraints.WEST;
        gc.gridheight = 2;
	    gc.gridwidth = 1;
        gc.gridx = 0;
        gc.gridy = 1;
        startMenuPanel.add(labelIcon1, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        startMenuPanel.add(labelIcon2, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        startMenuPanel.add(labelIcon3, gc);
	}
	
	/**
	 * Sets up the buttons and labels to display the state of the start menu panel where the user is asked to choose a map.
	 */
	public void mapChoiceDisplay(){
		button1.setText("Map 1");
		button2.setText("Map 2");
		button3.setText("Map 3");
		button3.setVisible(true);
		resetButton1.setVisible(false);
		resetButton2.setVisible(false);
		choiceLabel.setText("Choose map");
		gc.gridheight = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.CENTER;
		startMenuPanel.add(button1, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        startMenuPanel.add(button2, gc);
        gc.gridx = 1;
        gc.gridy = 3;
		startMenuPanel.add(button3, gc);
		
		labelIcon1.setIcon(res.getImgIcon("thumbMap1"));
		labelIcon2.setIcon(res.getImgIcon("thumbMap2"));
		labelIcon3.setIcon(res.getImgIcon("thumbMap3"));
		
        gc.gridx = 0;
        gc.gridy = 1;
        startMenuPanel.add(labelIcon1, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        startMenuPanel.add(labelIcon2, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        startMenuPanel.add(labelIcon3, gc);
	}
	
	/**
	 * Makes the status panel visible.
	 */
	public void showStatusPanel(){
		statusPanel.setVisible(true);
	}

	/**
	 * Makes the status panel invisible.
	 */
	public static void hideStatusPanel(){
		statusPanel.setVisible(false);
	}
	
	/**
	 * Makes the map panel visible.
	 */
	public static void showStartMenuPanel(){
		startMenuPanel.setVisible(true);
	}
	
	/**
	 * Makes the map panel invisible.
	 */
	public static void hideMapPanel(){
		mapPanel.setVisible(false);
	}
	
	/**
	 * Initialises the labels in the start menu panel.
	 */
	private void createLabels(){
		labelIcon1 = new JLabel(res.getImgIcon("player1Big"));
		labelIcon2 = new JLabel(res.getImgIcon("player2Big"));
		labelIcon3 = new JLabel(""); //res.getImgIcon("test")
		choiceLabel = new JLabel("Choose player");
		choiceLabel.setFont(choiceLabel.getFont().deriveFont(40f));
		gc.insets = new Insets(2,2,2,2);
		gc.anchor = GridBagConstraints.CENTER;
	    gc.gridwidth = 2;
	    gc.gridheight = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        startMenuPanel.add(choiceLabel, gc);
        gc.anchor = GridBagConstraints.WEST;
        gc.gridheight = 2;
	    gc.gridwidth = 1;
        gc.gridx = 0;
        gc.gridy = 1;
        startMenuPanel.add(labelIcon1, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        startMenuPanel.add(labelIcon2, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        startMenuPanel.add(labelIcon3, gc);
	}
	
	/**
	 * Initialises the menu and adds it to the frame.
	 */
	private void addMenu(){
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);

		JMenuItem backMenuItem = new JMenuItem("Back", KeyEvent.VK_B);
		backMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
		backMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(mapPanel.isShowing() == true){
					mapChoiceDisplay();
					hideMapPanel();
					hideStatusPanel();
					showStartMenuPanel();
					}
				else if(button1.getText().equals("Map 1")) {
					playerChoiceDisplay();
				}
			}
		});
		fileMenu.add(backMenuItem);

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

		setJMenuBar(menuBar);
	}

	/**
	 * Initialises the buttons with appropriate action listeners  and adds them to the start menu.
	 */
	private void createButtons(){
		button1 = new JButton("Player 1");
		button2 = new JButton("Player 2");
		button3 = new JButton("Map 3");
		resetButton1 = new JButton("Reset player");
		resetButton2 = new JButton("Reset player");
		button1.setPreferredSize(new Dimension(150, 40));
		button2.setPreferredSize(new Dimension(150, 40));
		button3.setPreferredSize(new Dimension(150, 40));
		
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(button1.getText().equals("Player 1")){
					mapChoiceDisplay();
					playerChoice = 1;
					// gör allt som ska göras när man trycker på player 1
				}else{
					// gör allt som ska göras när man trycker på map 1
					mapChoice = 1;
					chooseMapToBeCreated();
				}
			}
		}); 
		
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(button2.getText().equals("Player 2")){
					mapChoiceDisplay();
					playerChoice = 2;
				}else{
					mapChoice = 2;
					chooseMapToBeCreated();
				}
			}
		});
		
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mapChoice = 3;
				chooseMapToBeCreated();
			}
		});
		
		resetButton1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 try{
			         File file = new File("./bin/SavedPlayer1");
			         file.delete();
			         JOptionPane.showMessageDialog(startMenuPanel,
			        		    "Player 1 has been reset",
			        		    "Player 1 reset",
			        		    JOptionPane.INFORMATION_MESSAGE);
			      }catch(Exception f){
			         f.printStackTrace();
			      }
			}
		});
		
		resetButton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 try{
			         File file = new File("./bin/SavedPlayer2");
			         file.delete();
			         JOptionPane.showMessageDialog(startMenuPanel,
			        		    "Player 2 has been reset",
			        		    "Player 2 reset",
			        		    JOptionPane.INFORMATION_MESSAGE);
			      }catch(Exception f){
			         f.printStackTrace();
			      }
			}
		});

		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridheight = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.SOUTH;
		startMenuPanel.add(button1, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.NORTH;
        startMenuPanel.add(resetButton1, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.SOUTH;
        startMenuPanel.add(button2, gc);
        gc.gridx = 1;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.NORTH;
		startMenuPanel.add(resetButton2, gc);
        gc.gridx = 1;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.NORTH;
		startMenuPanel.add(button3, gc);
		button3.setVisible(false);
	}
	
	/**
	 * Creates a map in the map panel when the user chooses which map to play.
	 * Hides the start menu panel.
	 * Shows the status panel and adds the map panel to the frame.
	 */
	public void chooseMapToBeCreated(){
		mapPanel.createMap(mapChoice,playerChoice);
		mapPanel.setVisible(true);
		mapPanel.setFocusable(true);
		startMenuPanel.setVisible(false);
		showStatusPanel();
		add(mapPanel,BorderLayout.CENTER);
	}
}
