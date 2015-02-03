import java.awt.*;
import java.io.File;
import java.util.*;

import javax.swing.ImageIcon;


public class Map {
	private static final int MAPSIZE = 14;
	
	private Scanner m;
	private String Map[] = new String[MAPSIZE];
	
	private Image grass, wall;
	
	public Map(){
		ImageIcon img = new ImageIcon("./res/grass.png");
		grass = img.getImage();
		img =  new ImageIcon("./res/wall.png");
		wall = img.getImage();
		
		openFile();
		readFile();
		closeFile();
	}
	
	public Image getGrass(){
		return grass;
	}	
	
	public Image getWall(){
		return wall;
	}
	
	public String getMap(int x, int y){
		String index = Map[y].substring(x, x+1);
		return index;
	}
	
	public void openFile(){
		try{
			m =  new Scanner(new File("./res/map_1.txt"));
		}catch(Exception e){
			System.out.println("error loading maperino");
		}
	}
	
	public void readFile(){
		while(m.hasNext()){
			for(int i=0; i<MAPSIZE; i++){
				Map[i] = m.next();
			}
			
		}
	}
	
	public void closeFile(){
		m.close();
	}
}
