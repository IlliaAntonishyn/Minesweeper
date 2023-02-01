import java.util.Random;

public class PlayField {
	
	private Random rng;
	private boolean first;

	public PlayField() {
		rng = new Random();
		
		Main.inGame = true;
		Main.victory = false;
		Main.defeat = false;
				
		Main.field = new int[Main.fieldHeight][Main.fieldWidth];
		Main.screen = new int[Main.fieldHeight][Main.fieldWidth];
		
		setBombs();
		setNumbers();
		setScreen();
	}
	
	public void updateField(int clickX, int clickY, int clickType) {
		first = true;
		if(clickY >= 0) {
			if(clickType == 0) {
				if(Main.field[clickY][clickX] != 9) {
					clearScreen(clickX, clickY);
				}else {
					revealBombs(clickX, clickY);
					Main.inGame = false;
					Main.defeat = true;
				}
			}else if(clickType == 1){ 
				if(Main.screen[clickY][clickX] == 1){
					Main.screen[clickY][clickX] = 3;
					Main.displayBombs--;
				}else if(Main.screen[clickY][clickX] == 3) {
					Main.screen[clickY][clickX] = 1;
					Main.displayBombs++;
				}
			}
			checkVictory();
			
			Main.drawField();
		}
	}
	
	private void checkVictory() {
		int flagCount = 0;
		int count = 0;
		int blanks = 0;
		for(int i = 0; i < Main.fieldHeight; i++) {
			for(int j = 0; j < Main.fieldWidth; j++) {
				if(Main.screen[i][j] == 1) {
					blanks++;
				}else if(Main.screen[i][j] == 3) {
					flagCount++;
					if(Main.field[i][j] == 9) {
						count++;
					}
				}				
			}
		}
		if(count == Main.bombCount && count == flagCount && blanks == 0) {
			Main.inGame = false;
			Main.victory = true;
		}
	}

	private void clearScreen(int x, int y) {
		Main.screen[y][x] = 0;
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(y + i >= 0 && x + j >= 0 && y + i < Main.fieldHeight && x + j < Main.fieldWidth) {
					if(Main.field[y][x] != 0 && first) {
						if(Main.field[y + i][x + j] == 0) {
							first = false;
							clearScreen(x + j, y + i);
						}
					}else {
						if(Main.screen[y + i][x + j] == 1 && Main.field[y + i][x + j] != 9) {
							Main.screen[y + i][x + j] = 0;
							if(Main.field[y + i][x + j] == 0) {
								first = false;
								clearScreen(x + j, y + i);
							}
						}
					}
				}
			}
		}
	}

	private void revealBombs(int x, int y) {
		for(int i = 0; i < Main.fieldHeight; i++) {
			for(int j = 0; j < Main.fieldWidth; j++) {
				if(Main.field[i][j] == 9) {
					Main.screen[i][j] = 0;
				}
			}
		}
		Main.screen[y][x] = 2;
	}
	
	private void setNumbers() {
		int number;
		for(int y = 0; y < Main.fieldHeight; y++) {
			for(int x = 0; x < Main.fieldWidth; x++) {
				if(Main.field[y][x] != 9) {
					number = 0;
					for(int i = -1; i < 2; i++) {
						for(int j = -1; j < 2; j++) {
							if(y + i >= 0 && x + j >= 0 && y + i < Main.fieldHeight && x + j < Main.fieldWidth) {
								if(Main.field[y + i][x + j] == 9) number++;
							}
						}
					}
					Main.field[y][x] = number;
				}
			}
		}
	}
	
	private void setBombs() {
		int count = 0;
		int x;
		int y;
		while(count < Main.bombCount){
			x = rng.nextInt(Main.fieldWidth);
			y = rng.nextInt(Main.fieldHeight);
			if(Main.field[y][x] != 9) {
				Main.field[y][x] = 9;
				count++;
			}
		}
	}
	
	private void setScreen() {
		for(int i = 0; i < Main.fieldHeight; i++) {
			for(int j = 0; j < Main.fieldWidth; j++) {
				Main.screen[i][j] = 1;
			}
		}
	}
	
}
