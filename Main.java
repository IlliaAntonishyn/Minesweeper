import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {
	
	static public StartPanel startPanel;
	static public GamePanel gamePanel;
	static public PlayField play;
	static public Frame frame;
	
	static int fieldHeight;
	static int fieldWidth;
	static int bombCount;
	static int displayBombs;
	
	static public int[][] field;
	static public int[][] screen;
		
	static public boolean inGame;
	static public boolean victory;
	static public boolean defeat;
	
	static public Dimension screenSize;
	
	public static void main(String[] args) {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame = new Frame();
		
		insertParameters();
	}
	
	public static void insertParameters() {
		startPanel = new StartPanel();
		frame.setTitle("Minesweeper - Start Screen");
		frame.getContentPane().setPreferredSize(new Dimension(StartPanel.startPanelWidth, StartPanel.startPanelHeight));
		frame.setLocation((screenSize.width - StartPanel.startPanelWidth) / 2, (screenSize.height - StartPanel.startPanelHeight) / 2);
		frame.add(startPanel);
		frame.pack();
	}
	
	public static void createField() {
		displayBombs = bombCount;
		play = new PlayField();
		gamePanel = new GamePanel();
		frame.setTitle("Minesweeper - Game");
		frame.setLocation((screenSize.width - fieldWidth * 64) / 2, (screenSize.height - fieldHeight * 64) / 2);
		frame.getContentPane().setPreferredSize(new Dimension(fieldWidth * 64, fieldHeight * 64 + 64));
		frame.add(gamePanel);
		frame.pack();
	}
	
	public static void drawField() {
		gamePanel.repaint();
	}
	
	public static void updateField(int clickX, int clickY, int clickType) {
		play.updateField(clickX, clickY, clickType);
	}
}
