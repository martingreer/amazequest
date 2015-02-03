import java.awt.Graphics;

public class GameEngine implements Runnable {
	private static final int MAPSIZE = 14;
	private static final int TILESIZE = 32;
	
	Board board = new Board();
	
	private Map map;
	/*
	public void drawBoard(Graphics map){
		super.paint(b);
		for(int y=0; y<MAPSIZE;y++){
			for(int x=0; x<MAPSIZE; x++){
				if(map.getMap(x, y).equals(".")){
					g.drawImage(m.getGrass(), x*TILESIZE, y*TILESIZE, null);
				}
				if(m.getMap(x, y).equals("W")){
					g.drawImage(m.getWall(), x*TILESIZE, y*TILESIZE, null);
				}
			}
			
		}
	}
	*/
	public void run(){
		// Code for repainting board every ~25ms
	}
}
