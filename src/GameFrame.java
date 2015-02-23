
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;


@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private static final int WINDOW_SIZE_X = 456;
	private static final int WINDOW_SIZE_Y = 651;
	private static String VERSION = "A Maze Quest " + "\n" + "Version:" + " Alpha 13.37" + "\n" + "© Jonas Brothers";
	private JMenuBar menuBar;
	private StatusPanel statusPanel;
	private JPanel startmenuPanel;
	private MapPanel mapPanel;
	
	public GameFrame (String title){
		super(title);	
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
		setResizable(true);
		setLocationRelativeTo(null);
		
		statusPanel = new StatusPanel();
		Container c = getContentPane();
		c.add(statusPanel, BorderLayout.SOUTH);
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
		startmenuPanel = new StartmenuPanel() ;
		
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
		JButton playButton;
		JButton settingsButton;
		JButton helpButton;
		JButton backButton;

		add(startmenuPanel,BorderLayout.CENTER);
		playButton = ((StartmenuPanel) startmenuPanel).getPlayButton();
		settingsButton= ((StartmenuPanel) startmenuPanel).getSettingsButton();
		helpButton = ((StartmenuPanel) startmenuPanel).getHelpButton();
		backButton = ((StartmenuPanel) startmenuPanel).getBackButton();
		backButton.setVisible(false);
	
		
		 playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startmenuPanel.setVisible(false);
				setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
				setResizable(true);
				setVisible(true);
				showStatusPanel();
				mapPanel = new MapPanel();
				add(mapPanel);
				(new Thread(new GameThread(mapPanel, statusPanel))).start();

			}
		}); 
	
		 
		helpButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			playButton.setVisible(false);
			settingsButton.setVisible(false);
			helpButton.setVisible(false);
			String helpInfo = "\t" + "                        " +"Welcome to A Maze Quest" + "\n" + "kill all enemies";
			JTextArea helpText = new JTextArea();
			helpText.setEditable(false);
			helpText.setText(helpInfo);
			add(helpText, BorderLayout.NORTH);
			
			backButton.setVisible(true);
		
			
			backButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					playButton.setVisible(true);
					settingsButton.setVisible(true);
					helpButton.setVisible(true);
					backButton.setVisible(false);
					helpText.setVisible(false);
				}
			});

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
		}
	});
	}



}
