package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;
import Model.ImageResources;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private static final int WINDOW_SIZE_X = 20*32+16;
	private static final int WINDOW_SIZE_Y = 20*32+208;
	private static String VERSION = "A Maze Quest " + "\n" + "Version:" + " Alpha 13.37" + "\n" + "© Jonas Brothers";
	private ImageResources res = new ImageResources();
	private JMenuBar menuBar;
	private static StatusPanel statusPanel;
	private static JPanel startMenuPanel;
	private static MapPanel mapPanel;
	private JLabel labelIcon1, labelIcon2, labelIcon3;
	private JLabel choiceLabel;
	private JButton button1, button2, button3;
	private JButton resetButton1, resetButton2;
	private int playerChoice, mapChoice;
	private GridBagConstraints gc;
	
	public GameFrame (String title){
		super(title);	
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
		setResizable(true);
		setLocationRelativeTo(null);
		statusPanel = new StatusPanel();
		mapPanel = new MapPanel(statusPanel);
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
	
	public void showStatusPanel(){
		statusPanel.setVisible(true);
	}

	public static void hideStatusPanel(){
		statusPanel.setVisible(false);
	}
	
	public static void hideMapPanel(){
		mapPanel.setVisible(false);
	}
	
	public static void showStartMenuPanel(){
		startMenuPanel.setVisible(true);
	}
	
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
	
	public void chooseMapToBeCreated(){
		mapPanel.createMap(mapChoice,playerChoice);
		mapPanel.setVisible(true);
		mapPanel.setFocusable(true);
		startMenuPanel.setVisible(false);
		showStatusPanel();
		add(mapPanel,BorderLayout.CENTER);
	}
}
