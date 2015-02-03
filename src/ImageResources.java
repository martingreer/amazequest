import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class ImageResources {
	
	File file = new File("./res/ImagePaths.txt");
	
	public ImageResources(){
		
	}
	
	public String getPath(String ID){
		try {
		    Scanner scanner = new Scanner(file);

		    //now read the file line by line...
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        String[] parts = line.split(":");
		        if(parts[0].equals(ID)) { 
		            return parts[1];
		        }
		    }
		} catch(FileNotFoundException e) { 
			JOptionPane.showMessageDialog(null, "File could not be found");
		}
		return null;
	}
}
