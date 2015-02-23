

public class Main{
	private static GameFrame gameFrame;

	public static void main(String[] args){
		gameFrame = new GameFrame("A Maze Quest");
		gameFrame.setVisible(true);
	}

	public static void setGameFalse() {
		gameFrame.setVisible(false);
	}

}
