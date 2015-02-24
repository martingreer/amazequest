
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.*;


@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private static final int WINDOW_SIZE_X = 456;
	private static final int WINDOW_SIZE_Y = 651;
	private static String VERSION = "A Maze Quest " + "\n" + "Version:" + " Alpha 13.37" + "\n" + "© Jonas Brothers";
	private JMenuBar menuBar;
	private StatusPanel statusPanel;
	private JPanel startMenuPanel;
	private MapPanel mapPanel;
	private JButton button1;
	private JButton button2;
	private static Player loadedPlayer = null;		// only here for load/save , remove if changed
	private static Map mapRef = null;							// only here for load/save , remove if changed

	public GameFrame (String title){
		super(title);	
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
		setResizable(true);
		setLocationRelativeTo(null);
		mapPanel = new MapPanel();
		add(mapPanel);
		mapPanel.setVisible(false);
		startMenuPanel = new JPanel(new GridBagLayout());
		add(startMenuPanel,BorderLayout.CENTER);
		statusPanel = new StatusPanel();
		add(statusPanel, BorderLayout.SOUTH);
		hideStatusPanel();
		addMenu();
		createButtons();
		
	}

	public void showStatusPanel(){
		statusPanel.setVisible(true);
	}

	public void hideStatusPanel(){
		statusPanel.setVisible(false);
	}

	private void addMenu(){
		menuBar = new JMenuBar();


		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);

		//Sub-choice of File menu
		JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		saveMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(loadedPlayer == null){
					JOptionPane.showMessageDialog(null,"Can not save without starting a new game stoopid!");
				}else{
					mapRef.save("PlayerState.bin");
				}
			}
		});
		fileMenu.add(saveMenuItem);

		JMenuItem loadMenuItem = new JMenuItem("Load", KeyEvent.VK_L);
		loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		loadMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//JOptionPane.showMessageDialog(null,"Game loaded");
				if(mapRef == null){
					load("PlayerState.bin");
				}else{
					mapRef.load("PlayerState.bin");
				}
			}
		});
		fileMenu.add(loadMenuItem);

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
		setJMenuBar(menuBar);
	}

	private void createButtons(){
		button1 = new JButton("Player 1");
		button2 = new JButton("Player 2");

		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
				setResizable(true);
				setVisible(true);

				if(button1.getText().equals("Player 1")){
					button1.setText("Map 1");
					button2.setText("Map 2");
					// gör allt som ska göras när man trycker på player 1
				}else{
					// gör allt som ska göras när man trycker på map 1
					mapPanel.createMap(1);
					mapPanel.setVisible(true);
					mapPanel.setFocusable(true);
					startMenuPanel.setVisible(false);
					showStatusPanel();
					add(mapPanel,BorderLayout.CENTER);
					(new Thread(new GameThread(mapPanel, statusPanel))).start();
				}
			}
		}); 
		startMenuPanel.add(button1);

		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
				setResizable(true);
				setVisible(true);

				if(button2.getText().equals("Player 2")){
					button1.setText("Map 1");
					button2.setText("Map 2");
					// gör allt som ska göras när man trycker på player 1
				}else{
					// gör allt som ska göras när man trycker på map 1
					mapPanel.createMap(2);
					mapPanel.setVisible(true);
					mapPanel.setFocusable(true);
					startMenuPanel.setVisible(false);
					showStatusPanel();
					add(mapPanel,BorderLayout.CENTER);
					(new Thread(new GameThread(mapPanel, statusPanel))).start();
				}

			}
		});
		startMenuPanel.add(button2);	

	}

	public void load(String fileName) {				// NOTE!! load-method in both GameFrame and Map
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			loadedPlayer = (Player)in.readObject();
			in.close();
			System.out.println("Player loaded");
		}
		catch(Exception e) {
			System.out.println("LOAD FAILED \n");
			e.printStackTrace();
			System.exit(0);

		}
	}


}
