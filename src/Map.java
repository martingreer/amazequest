import java.awt.*;
import java.io.File;
import java.util.*;

import javax.swing.ImageIcon;


public class Map {
	
	private static final int MAP_SIZE = 14;

	private Scanner m;
	private Tile[][] tiles = new Tile[MAP_SIZE][MAP_SIZE];

	public Map(){
		openFile();
		readFile();
		closeFile();
	}

	public void openFile(){
		try{
			m =  new Scanner(new File("./res/map_1.txt"));
		}catch(Exception e){
			System.out.println("Error: Map failed to load");
		}
	}

	public void readFile(){
		char[] c;
		while(m.hasNext()){
			for(int y=0; y<MAP_SIZE; y++){
				c = m.next().toCharArray();
				for(int x=0; x<MAP_SIZE; x++){
					if(c[x] == 'W'){
						tiles[x][y] = new Tile(x, y, "wall", true);
					}
					else if(c[x] == '.'){
						tiles[x][y] = new Tile(x, y, "grass", false);
					}
				}
			}

		}
	}

	public Tile getTile(int x, int y){
		return tiles[x][y];
	}

	public void closeFile(){
		m.close();
	}
}
