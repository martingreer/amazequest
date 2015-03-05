package Model;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * This class is used to return requested images or image-paths to view-objects.
 * 
 * @author Johannes Uhr
 * @version 2015-02-22
 *
 */
public class ImageResources {
	
	/**
	 * The file that holds all keywords and their paths
	 */
	private File file;
	
	/**
	 * The constructor sets "file" to the txt-file that contains IDs and paths seperated by a ":".
	 */
	public ImageResources(){
		file = new File("./res/ImagePaths.txt");
	}
	
	/**
	 * This method scans the txt-file for an ID and if found, returns the path connected to the ID.
	 * 
	 * @param String that resembles the keyword connected to a path.
	 * @return String of the path to wanted image.
	 */
	public String getPath(String ID){
		try {
			Scanner scanner = new Scanner(file);

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

	/**
	 * This method returns the Image of the path connected to the parameter.
	 * 
	 * @param String that resembles the keyword connected to a path.
	 * @return Image that is connected to parameter.
	 */
	public Image getImg(String s){
		ImageIcon imgIcon  = new ImageIcon(getPath(s));
		Image img = imgIcon.getImage();
		return img;
	}

	/**
	 * This method returns the ImageIcon of the path connected to the parameter.
	 * 
	 * @param String that resembles the keyword connected to a path.
	 * @return Image that is connected to parameter.
	 */
	public ImageIcon getImgIcon(String s){
		return new ImageIcon(getPath(s));
	}
}
