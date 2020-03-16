import java.util.Scanner;

public class Vigenere {

	Scanner scanner = new Scanner(System.in);
	String phrase = "";
	String keyword = "";
	String Finalz = "";
	String encryptdecrypt = "";
	int[] keyword2;
	
	
	public Vigenere() {
		System.out.println("Enter your phrase");
		phrase = scanner.nextLine();
		phrase = phrase.toLowerCase();
		System.out.println("Enter your keyword");
		keyword = scanner.nextLine();
		keyword = keyword.toLowerCase();
		System.out.println("Encrypt or Decrypt?");
		encryptdecrypt = scanner.nextLine();
		if (encryptdecrypt.equals("Encrypt")) {
			Finalz = "";
			initializeKeyword();
			encryptVigenere();
		} 
		else if (encryptdecrypt.equals("Decrypt")) {
			Finalz = "";
			initializeKeyword();
			decryptVigenere();
		}		
	}
	

	


	private void encryptVigenere() {
		int keepGoing = phrase.length();
		//System.out.println(keepGoing);
		int yeet = 0;
		int keywordCounter = 0;
		while (yeet != keepGoing) {
			int shift = keyword2[keywordCounter % keyword2.length] - 97;
			//System.out.println(phrase.charAt(yeet));
			char temp = phrase.charAt(yeet);
			if (temp == ' ') {
				temp = ' ';
			}
			else {
				keywordCounter++;
				temp = (char) (temp + shift % 26);
				if ((int)temp > 122) {
					temp = (char) (temp - 26);
				}
			}
			Finalz = Finalz + temp;
			//System.out.println(temp);
			yeet++;
		}
		System.out.println(Finalz);
	}


	private void decryptVigenere() {
		int keepGoing = phrase.length();
		//System.out.println(keepGoing);
		int yeet = 0;
		int keywordCounter = 0;
		while (yeet != keepGoing) {
			int shift = keyword2[keywordCounter % keyword2.length] - 97;
			//System.out.println(shift);
			//System.out.println(phrase.charAt(yeet));
			char temp = phrase.charAt(yeet);
			if (temp == ' ') {
				temp = ' ';
			}
			else {
				//System.out.println(temp + "," + (int)temp);
				keywordCounter++;
				//System.out.println(shift % 26);
				temp = (char) (temp - shift % 26);
				//System.out.println(temp + "," + (int)temp);
				if ((int)temp < 97) {
					temp = (char) (temp + 26);
				}
				//System.out.println(temp + "," +(int)temp);
			}
			Finalz = Finalz + temp;
			yeet++;
		}
		System.out.println(Finalz);
	}
	
	private void initializeKeyword() {
		if (keyword.contains(" ")) {
			System.out.println("Your keyword cannot contain any spaces.");
			System.exit(0);
		}
		int temp [] = new int[keyword.length()];
		for (int i = 0; i < keyword.length(); i++) {
			temp[i] = keyword.charAt(i);
			//System.out.println(temp[i]);
		}
		keyword2 = temp;
		for (int i = 0; i < keyword2.length; i++) {
			//System.out.println(keyword2[i]);
		}
	}


	public static void main(String[] args) {
		new Vigenere();
	}

}
