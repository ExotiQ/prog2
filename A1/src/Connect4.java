package aufgabe1;

public class Connect4 {
	
	private static final int VERTICAL = 8;
	private static final int HORIZONTAL = 8;
	private static char[][] connect4 = new char[VERTICAL][HORIZONTAL];
	private static int currentPlayer = 0;
	private static int playPiecesUsed = 0;
	
	public static void main(String[] args) {
		currentPlayer = 1;
		initiateMatrix();
		while(true) {
			int number = Input.read();
			turn(number);
			printMatrix();
		}
	}
	
	public static void initiateMatrix() {
		for(int i = 0; i < VERTICAL; i++) {
			for(int j = 0; j < HORIZONTAL; j++) {
				connect4[i][j] = '.';
			}
		}
	}
	
	public static void printMatrix() {
		for(int i = 0; i < VERTICAL; i++) {
			for(int j = 0; j < HORIZONTAL; j++) {
				System.out.print(connect4[i][j]);
				System.out.print("|");
			}
			System.out.println("");
			for(int j = 0; j < HORIZONTAL*2; j++) {
			}
		}
		System.out.println("1 2 3 4 5 6 7 8");
	}
	
	public static void turn(int number) {
		number--;
		char playPiece = '.';
		switch(currentPlayer) {
			case 1: 				//Player1 uses X
				playPiece = 'X';
				break;
			case 2: 				//Player2 uses O
				playPiece = 'O';
				break;
			default: 
				System.out.println("Spiel wurde noch nicht gestartet");
				return;
		}
		for(int i = VERTICAL - 1; i >= 0; i--) {
			if(connect4[i][number] == '.') {
				connect4[i][number] = playPiece;
				playPiecesUsed++;
				checkMatrix(i, number, playPiece);
				if(currentPlayer == 1) {
					currentPlayer = 2;
					return;
				}
				if(currentPlayer == 2) {
					currentPlayer = 1;
					return;
				}
				return;
			}
		}
		System.out.println("Kein Platz in der Spalte");
	}
	@SuppressWarnings("Duplicates")
	public static boolean checkRows(int i, int j, char toBeChecked) {
		int count = 0;
		//checking Horizontal line starting at the last position a piece was inserted into
		for(int a = j; a < HORIZONTAL; a++) {
			if(connect4[i][a] == toBeChecked) {
				count++;
			} else {
				break;
			}
			if(count == 4) {
				return true;
			}
		}
		//count--;	//to prevent nullpointer we start at the same location and have to subtract one
		for(int b = j-1; b >= 0; b--) {
			if(connect4[i][b] == toBeChecked) {
				count++;
			} else {
				break;
			}
			if(count == 4) {
				return true;
			}
		}
		return false;
	}
	@SuppressWarnings("Duplicates")
	public static boolean checkColumns(int i, int j, char toBeChecked) {
		int count = 0;	//resetting count
		//checking Vertical line starting at the last position a piece was inserted into
		for(int a = i; a < VERTICAL; a++) {
			if(connect4[a][j] == toBeChecked) {
				count++;
			} else {
				break;
			}
			if(count == 4) {
				return true;
			}
		}
		//count--; 	//to prevent nullpointer we start at the same location and have to subtract one
		for(int b = i-1; b >= 0; b--) {
			if(connect4[b][j] == toBeChecked) {
				count++;
			}
			else {
				break;
			}
			if(count == 4) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkPositiveDiagonal(int i, int j, char toBeChecked) {
		int count = 0;	//resetting count
		//checking Diagonal (positive slope) line starting at the last position a piece was inserted into
		for(int a = i, b = j; a < VERTICAL && b >= 0; a++, b--) {
			if(connect4[a][b] == toBeChecked) {
				count++;
			} else {
				break;
			}
			if(count == 4) {
				return true;
			}
		}
		//count--;	//to prevent nullpointer we start at the same location and have to subtract one
		for(int a = i-1, b = j+1; a >= 0 && b < HORIZONTAL; a--, b++) {
			if(connect4[a][b] == toBeChecked) {
				count++;
			} else {
				break;
			}
			if(count == 4) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkNegativeDiagonal(int i, int j, char toBeChecked) {
		int count = 0;	//resetting count
		//checking Diagonal (negative slope) line starting at the last position a piece was inserted into
		for(int a = i, b = i; a < VERTICAL && b < HORIZONTAL; a++, b++) {
			if(connect4[a][b] == toBeChecked) {
				count++;
			} else {
				break;
			}
			if(count == 4) {
				return true;
			}
		}
		//count--;	//to prevent nullpointer we start at the same location and have to subtract one
		for(int a = i-1, b = j-1; a >= 0 && b >= 0; a--, b--) {
			if(connect4[a][b] == toBeChecked) {
				count++;
			} else {
				break;
			}
			if(count == 4) {
				return true;
			}
		}
		return false;
	}
	
	public static void checkMatrix(int i, int j, char toBeChecked) { 
		if(checkRows(i, j, toBeChecked) || checkColumns(i, j, toBeChecked) 
				|| checkPositiveDiagonal(i, j, toBeChecked) || checkNegativeDiagonal(i, j, toBeChecked)) {
			printMatrix();
			System.out.println("Spieler " + currentPlayer + " hat das Spiel gewonnen.");
			System.exit(0);
		}
		if(VERTICAL*HORIZONTAL == playPiecesUsed) {
			printMatrix();
			System.out.println("Unentschieden");
			System.exit(0);
		}
	}
}
