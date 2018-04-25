package aufgabe1;

public class Connect4 {

	private char[][] connect4;
	private int currentPlayer = 0;
	
	public Connect4(int player, char[][] field) {
		this.currentPlayer = player;
		connect4 = field;
	}
	
	public void initiateMatrix() {
		for(int i = 0; i < connect4.length; i++) {
			for(int j = 0; j < connect4[0].length; j++) {
				connect4[i][j] = '.';
			}
		}
	}
	
	public void printMatrix() {
		for(int i = 0; i < connect4.length; i++) {
			for(int j = 0; j < connect4[0].length; j++) {
				System.out.print(connect4[i][j]);
				System.out.print("|");
			}
			System.out.println("");
			for(int j = 0; j < connect4[0].length*2; j++) {
				System.out.print("-");
			}
			System.out.println("");
		}
		System.out.println("1 2 3 4 5 6 7 8");
	}
	
	public void turn(int number) {
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
		for(int i = connect4.length - 1; i >= 0; i--) {
			if(connect4[i][number] == '.') {
				connect4[i][number] = playPiece;
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
	
	public boolean checkRows(int i, int j, char toBeChecked) {
		int count = 0;
		//checking connect4[0].length line starting at the last position a piece was inserted into
		for(int a = j; a < connect4[0].length; a++) {
			if(connect4[i][a] == toBeChecked) {
				count++;
			} else {
				break;
			}
			if(count == 4) {
				return true;
			}
		}
		count--;	//to prevent nullpointer we start at the same location and have to subtract one
		for(int b = j; b >= 0; b--) {
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
	
	public boolean checkColumns(int i, int j, char toBeChecked) {
		int count = 0;	//resetting count
		//checking connect4.length line starting at the last position a piece was inserted into
		for(int a = i; a < connect4.length; a++) {
			if(connect4[a][j] == toBeChecked) {
				count++;
			} else {
				break;
			}
			if(count == 4) {
				return true;
			}
		}
		count--; 	//to prevent nullpointer we start at the same location and have to subtract one
		for(int b = i; b >= 0; b--) {
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
	
	public boolean checkPositiveDiagonal(int i, int j, char toBeChecked) {
		int count = 0;	//resetting count
		//checking Diagonal (positive slope) line starting at the last position a piece was inserted into
		for(int a = i, b = j; a < connect4.length && b >= 0; a++, b--) {
			if(connect4[a][b] == toBeChecked) {
				count++;
			} else {
				break;
			}
			if(count == 4) {
				return true;
			}
		}
		count--;	//to prevent nullpointer we start at the same location and have to subtract one
		for(int a = i, b = j; a >= 0 && b < connect4[0].length; a--, b++) {
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
	
	public boolean checkNegativeDiagonal(int i, int j, char toBeChecked) {
		int count = 0;	//resetting count
		//checking Diagonal (negative slope) line starting at the last position a piece was inserted into
		for(int a = i, b = i; a < connect4.length && b < connect4[0].length; a++, b++) {
			if(connect4[a][b] == toBeChecked) {
				count++;
			} else {
				break;
			}
			if(count == 4) {
				return true;
			}
		}
		count--;	//to prevent nullpointer we start at the same location and have to subtract one
		for(int a = i, b = j; a >= 0 && b >= 0; a--, b--) {
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
	
	public void checkMatrix(int i, int j, char toBeChecked) { 
		if(checkRows(i, j, toBeChecked) || checkColumns(i, j, toBeChecked) 
				|| checkPositiveDiagonal(i, j, toBeChecked) || checkNegativeDiagonal(i, j, toBeChecked)) {
			printMatrix();
			System.out.println("Spieler " + currentPlayer + " hat das Spiel gewonnen.");
			System.exit(0);
		}
	}
}
