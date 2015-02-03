import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class StartMenu extends GameWindow{

	
	public static void createButtons(JFrame mainwindow){
		
		//PlayButton
		JButton playButton = new JButton("Play!");
		playButton.setPreferredSize(new Dimension(170, 130));
		playButton.setFont(new Font("Arial", Font.ITALIC, 45));
		//playButton.setLayout(null);
		mainwindow.add(playButton);
		
		playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "game on");
			}
			
		});
		//Other buttons
		
	} 
}
