import java.awt.*;
import java.io.File;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Map extends JPanel {
	
	private static final boolean DEBUG = false;
	private static final int MAP_SIZE = 14;
	private static final int TILE_SIZE = 32;

	private Scanner m;
	private Tile[][] tiles = new Tile[MAP_SIZE][MAP_SIZE];
	private Player player;
	private Tile playerTile;
	private ImageResources res = new ImageResources();

	public Map(){
		if(DEBUG){System.out.println("DEBUG: Board constructor initiated.");}
		
		
		setFocusable(true);
		
		openFile();
		readFile();
		closeFile();
		
		player = new Player(1,1,5,"Bertil",0);
		tiles[1][1].setPlayer(player);
		playerTile = tiles[1][1];
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

	public void closeFile(){
		m.close();
	}
	
	public void paint(Graphics g){
		if(DEBUG){System.out.println("DEBUG: Attempting to draw the board.");}
		super.paint(g);
		for(int y=0; y<MAP_SIZE;y++){
			for(int x=0; x<MAP_SIZE; x++){
				g.drawImage(res.getImg(tiles[x][y].getImgID()), x*TILE_SIZE, y*TILE_SIZE, null);	
				if(tiles[x][y].getPlayer() != null){
					g.drawImage(res.getImg("playerSouth"), x*TILE_SIZE, y*TILE_SIZE, null);
				}
			}
		}
		
	}
}
