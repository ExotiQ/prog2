package aufgabe1;

import java.io.IOException;

public class Input {

	public static int read() {
		while(true) {
			try {
				int row = System.in.read();
				System.in.read();
				if(row >= '1' && row <= '8') {
					return row - '0';
				} else {
					System.out.println(row);
					System.out.println("Falsche Eingabe, bitte gib eine Zahl zwischen 1 und 8 ein.");
				}
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
			}
		}
	}
}
