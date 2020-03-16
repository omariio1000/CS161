import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/* 
*This is a binary translator that can translate from binary to decimal or decimal to binary
*Author: Omar Nassar
*Date: September 11, 2018
*/


public class BinaryTranslator {

	private Scanner scanner;
	private Scanner fileScanner;

	public BinaryTranslator() {
		System.out.println("Please enter \"file\" to enter a file or \"input\" to use the console.");
		scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String numberInput = "";
		if (input.equals("file")) { //input from file
			try {
				System.out.println("Enter your file name.");
				input = scanner.nextLine();
				fileScanner = new Scanner(new File(input));
				numberInput = fileScanner.nextLine();
			} catch(IOException ex) {
				System.out.println("File was not found.");
				scanner.close();
				System.exit(1);
			}
		}
		else if (input.equals("input")) { //input from console
			System.out.println("Input your decimal/binary.");
			numberInput = scanner.nextLine();
		}
		else {
			System.out.println("Invalid input.");
			System.exit(0);
		}
		System.out.println("If you are translating from decimal to binary, type \"dtb\".");
		System.out.println("If you are translating from binary to decimal, type \"btd\".");
		input = scanner.nextLine();
		if (input.equals("dtb")) { //decimal to binary
			String answer = "";
			int number = Integer.parseInt(numberInput);
			int twoPower = (int)(Math.log10(number) / Math.log10(2));
			for (int index = twoPower; index >= 0; index--) {
				if ((int)(number / Math.pow(2, index)) == 1) {
					answer += "1";
					number = (int) (number % Math.pow(2, index));
				}
				else {
					answer += "0";
				}
			}
			System.out.println(answer);
		}
		else if (input.equals("btd")) { //binary to decimal
			int answer = 0;
			int power = 0;
			for (int a = numberInput.length() - 1; a >= 0; a--) {
				if(numberInput.charAt(a)== '1') {
					answer = (int)(answer + Math.pow(2, power));
				}
				power++;
				
			}
			String stringAnswer = Integer.toString(answer);
			System.out.println(stringAnswer);
		}
		else {
			System.out.println("Invalid input.");
			System.exit(0);
		}
		scanner.close();
	}
	
	public static void main(String[] args) {
		new BinaryTranslator();
		// TODO Auto-generated method stub

	}

}
