import java.util.Scanner;
import static java.lang.Character.toLowerCase;

public class Scytale {

	Scanner scanner = new Scanner(System.in);
	int column;
	int row;
	String phrase = "";
	String newPhrase = "";
	String encryptdecrypt = "";
	String Finalz = "";
	char[][] band;

	public Scytale() {
		System.out.println("Enter your phrase.");
		phrase = scanner.nextLine();
		for (int i = 0; i < phrase.length(); i++) {
			char letter = phrase.charAt(i);
			letter = toLowerCase(letter);
			newPhrase += letter;
		}
		newPhrase = newPhrase.replaceAll("\\s", "");
		System.out.println("Enter your column height.");
		column = Integer.parseInt(scanner.nextLine());
		row = newPhrase.length() / column + 1;
		band = new char[column][row];
		int keepGoing = column * row - newPhrase.length();
		for (int i = 0; i < keepGoing; i++) {
			band[column - 1 - i][row - 1] = '@';
		}
		System.out.println("Encrypt or Decrypt?");
		encryptdecrypt = scanner.nextLine();
		if (encryptdecrypt.equals("Encrypt")) {
			Finalz = "";
			encryptScytale();
		} 
		else if (encryptdecrypt.equals("Decrypt")) {
			Finalz = "";
			decryptScytale();
		}

	}

	private void encryptScytale() {
		int letter = 0;
		for (int x = 0; x < column; x++) {
			for (int i = 0; i < row; i++) {
				if (band[x][i] == '@') {
					i++;
				} 
				else if (letter < newPhrase.length()) {
					band[x][i] = newPhrase.charAt(letter);
					letter++;
				} 
				else {
					band[x][i] = '@';
					letter++;
				}
			}
		}
		for (int i = 0; i < row; i++) {
			for (int x = 0; x < column; x++) {
				Finalz += band[x][i];
			}
		}
		System.out.println(Finalz);
		Finalz = Finalz.replaceAll("@", "");
		System.out.println(Finalz);
	}

	private void decryptScytale() {
		int letter = 0;
        for (int i = 0; i < row; i++) {
            for (int x = 0; x< column; x++) {
                if ( band[x][i] == '@') {
                    x++;
                }
                else if (letter < newPhrase.length()) {

                    band[x][i] = newPhrase.charAt(letter);
                    letter++;
                }
                else {

                    band[x][i] = '@';
                    letter++;
                }
            }
        }
        for ( int x = 0 ; x < column; x++) {
            for (int i = 0; i< row; i++) {
                if (band[x][i] == '@') {

                }
                else {
                    Finalz += band[x][i];
                }
            }
        }
        System.out.println(Finalz);
	}

	public static void main(String[] args) {
		new Scytale();
	}

}
