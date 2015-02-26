package Model;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ImageResources {

	File file;

	public ImageResources(){
		file = new File("./res/ImagePaths.txt");
	}

	public String getPath(String ID){
		try {
			Scanner scanner = new Scanner(file);

			//now read the file line by line...
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(":");
				if(parts[0].equals(ID)) {
					scanner.close();
					return parts[1];
				}
			}
			scanner.close();
			
		} catch(FileNotFoundException e) { 
			JOptionPane.showMessageDialog(null, "File could not be found");
		}
		return null;
	}

	public Image getImg(String s){
		ImageIcon imgIcon  = new ImageIcon(getPath(s));
		Image img = imgIcon.getImage();
		return img;
	}

	public ImageIcon getImgIcon(String s){
		return new ImageIcon(getPath(s));
	}
}
