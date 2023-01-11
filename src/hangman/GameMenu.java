package hangman;

import java.util.Scanner;

public class GameMenu {

	public static void printWelcome() {
		System.out.println("================ \n");
		System.out.println("=== HANG MAN === \n");
		System.out.println("================ \n");
		System.out.println("");

	}
	public static Boolean playAgainMenu() {
		Scanner s = new Scanner(System.in);
		Boolean playAgain = true;

		System.out.println("Play again ? Y/N");
		char playAgainOption = s.next().charAt(0);
		
		
		if(playAgainOption == 'y' ||playAgainOption == 'Y') {
			return playAgain = true;
		}else if((playAgainOption == 'n' ||playAgainOption == 'N')) {
			System.out.println("Goodbye!");
			return playAgain = false;
		}else {
			System.out.println("Invalid input!");
			System.out.println("Play again ? Y/N");
			 playAgainOption = s.next().charAt(0);
			 
		}
		return playAgain;
		
	}
	public static String[] printHiddenWord(char[] hiddenWord) {
		String[] toShow = new String[hiddenWord.length];
		for (int i = 0; i < hiddenWord.length; i++) {
			toShow[i] = String.valueOf(hiddenWord[i]);
		}
		return toShow;
	}
	public static void printHangman(Integer attempt, String word) {
		switch(attempt) {
		case 1:
			System.out.println(
					  "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "___|___");
			break;
		case 2:
			System.out.println(
					"____________\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "___|___");
			break;
		case 3:
			System.out.println(
					"____________\n"
					+ "   |	|\n"
					+ "   |	|\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "___|___");
			break;
		case 4:
			System.out.println(
					"____________\n"
					+ "   |	|\n"
					+ "   |	|\n"
					+ "   |	O\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "___|___");
			break;
		case 5:
			System.out.println(
					"____________\n"
					+ "   |	|\n"
					+ "   |	|\n"
					+ "   |	O\n"
					+ "   |	|\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "___|___");
			break;
		case 6:
			System.out.println(
					"____________\n"
					+ "   |	 |\n"
					+ "   |	 |\n"
					+ "   |	 O\n"
					+ "   |	/|\n"
					+ "   |\n"
					+ "   |\n"
					+ "   |\n"
					+ "___|___");
			break;
		case 7:
			System.out.println(
					"____________\n"
					+ "   |	 |\n"
					+ "   |	 |\n"
					+ "   |	 O\n"
					+ "   |	/|\n"
					+ "   |	/| \n"
					+ "   |\n"
					+ "   |\n"
					+ "___|___");
			break;
		case 8:
			System.out.println("GAME OVER !");
			System.out.printf("The word was : %s \n " ,word);
			break;
		default:
			System.out.println("invalid attempt");

		}
		

	}
	
	
}
