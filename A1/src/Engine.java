package aufgabe1;

public class Engine {
	public static void main(String[] args) {
		char[][] matrix = new char[8][8];
		Connect4 board = new Connect4(1, matrix);
		Input input = new Input();
		
		board.initiateMatrix();
		while(true) {
			int number = input.read();
			board.turn(number);
			board.printMatrix();
		}
	}
}
