import java.util.Scanner;

public class Caesar {
	private Scanner scanner;
	String Finalz = "";
	public Caesar() {
		scanner = new Scanner(System.in);
		System.out.println("Encrypt or Decrypt");
		String eord = new String(scanner.nextLine());
		if (eord.equals("Encrypt")) {
			Encrypt();
		}
		else if (eord.equals("Decrypt")) {
			Decrypt();
		}
	}
	
	private void Encrypt() {
		System.out.println("Enter the phrase you would like to encrypt");
		String phrase = new String(scanner.nextLine());
		System.out.println("Enter shift number");
		int shift = Integer.parseInt(scanner.nextLine());
		//System.out.println(phrase + "," + shift);
		int keepGoing = phrase.length();
		//System.out.println(keepGoing);
		int yeet = 0;
		while (yeet != keepGoing) {
			System.out.println(phrase.charAt(yeet));
			char temp = phrase.charAt(yeet);
			if (temp == ' ') {
				temp = ' ';
			}
			else {
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
	
	private void Decrypt() {
		System.out.println("Enter the phrase you would like to decrypt");
		String phrase = new String(scanner.nextLine());
		System.out.println("Enter shift number");
		int shift = Integer.parseInt(scanner.nextLine());
		//System.out.println(phrase + "," + shift);
		int keepGoing = phrase.length();
		//System.out.println(keepGoing);
		int yeet = 0;
		while (yeet != keepGoing) {
			//System.out.println(phrase.charAt(yeet));
			char temp = phrase.charAt(yeet);
			if (temp == ' ') {
				temp = ' ';
			}
			else {
				temp = (char) (temp - shift % 26);
				if ((int)temp < 122) {
					temp = (char) (temp + 26);
				}
			}
			Finalz = Finalz + temp;
			//System.out.println(temp);
			yeet++;
		}
		System.out.println(Finalz);
		
	}


	public static void main(String[] args) {
		new Caesar();

	}

}
