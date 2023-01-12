package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void newGame(String response) {
		System.out.println(response);
	}

	// Function that takes in a dictionary of words, and selects a random one.
	public static String newWord(File wordList) throws FileNotFoundException {
		// Scanner to read all lines in the wordlist
		Scanner textScanner = new Scanner(wordList);
		// Declare an Arraylist which will be used to store the words
		ArrayList<String> words = new ArrayList<>();
		// Read and add every word to the above array list.
		while (textScanner.hasNextLine()) {
			words.add(textScanner.nextLine());
		}
		// Select a random word and store it in the word variable.
		String word = words.get((int) (Math.random() * words.size()));
		System.out.println(word);

		return word;

	}

	public static void main(String[] args) throws Exception {
		// stores attempts the user has taken
		int attempts = 1;
		// Boolean to operate the while loop which denotes an active game.
		boolean activeGame = false;
		// A trigger boolean which the user will switch when a game has ended.
		boolean playAgain = false;
		// Scanner that takes in user input for the game.
		Scanner s = new Scanner(System.in);
		// path to the wordlist.
		File wordList = new File("/Users/param/Desktop/hangman/src/hangman/wordlist.txt");
//		File wordList = new File("./wordlist.txt");

		String word = newWord(wordList);

		// Convert our word into a character array
		char[] wordArr = word.toCharArray();
		// Create an array of underscores that will be shown to the user,
		// this is the same size as the word.
		char[] hiddenWord = new char[wordArr.length];
		// fill the array with underscores
		Arrays.fill(hiddenWord, '_');

//		System.out.printf("hidden %s \n", Arrays.toString(hiddenWord));

		String[] toShow = GameMenu.printHiddenWord(hiddenWord);
		while (!activeGame) {
			GameMenu.printWelcome();
			activeGame = true;

		}

		while (activeGame) {
			// If the user has chosen to play again, clear and reset all variables, and set
			// up a new word.
			if (playAgain) {
				System.out.println("play again");
				attempts = 0;
				word = newWord(wordList);
				// Convert our word into a character array
				wordArr = word.toCharArray();
				// Create an array of underscores that will be shown to the user,
				// this is the same size as the word.
				hiddenWord = new char[wordArr.length];
				// fill the array with underscores
				Arrays.fill(hiddenWord, '_');
				toShow = GameMenu.printHiddenWord(hiddenWord);
				playAgain = false;
			}
			if (!playAgain && attempts < 8) {
//				System.out.println(Arrays.toString(hiddenWord));

				// The hidden word to be displayed.
				System.out.printf("%s \n", String.join(" ", toShow));
				System.out.printf("Would you like to guess a letter (L) OR a word (w) \n");
				char guessType = s.next().charAt(0);

				if (guessType == 'w' || guessType == 'W') {
					System.out.println("Enter a Word to guess.");
					String wordGuess = s.next();
					wordGuess = wordGuess.toLowerCase();

					if (wordGuess.equals(word)) {
						// If the user guesses a word and it is correct, end the game and prompt the
						// user to replay or exit.
						System.out.println("Nice! you guessed the word ! \n");
						System.out.println("Play again ? Y/N");
						char playAgainOption = s.next().charAt(0);

						if (playAgainOption == 'y' || playAgainOption == 'Y') {
							playAgain = true;
						} else if ((playAgainOption == 'n' || playAgainOption == 'N')) {

							System.out.println("Goodbye!");
							return;
						} else {
							System.out.println("Invalid input!");
							System.out.println("Play again ? Y/N");
							playAgainOption = s.next().charAt(0);
						}

					} else {
						// If the user guesses a word and it is incorrect, print a hangman and continue
						// the game.
						attempts++;
						GameMenu.printHangman(attempts, word);
						System.out.printf("attempts %s \n", attempts);

					}
					// allow the user to input a single character at a time
				} else if (guessType == 'l' || guessType == 'L') {
					System.out.println("Enter a letter to guess.");
					System.out.printf("You have %s attempts remaining \n", 8 - attempts);
					char playerGuess = s.next().charAt(0);
					playerGuess = Character.toLowerCase(playerGuess);
					// count keeps track of the matches of correct letters in the word.
					Integer count = 0;
					for (int i = 0; i < wordArr.length; i++) {

						if (playerGuess == wordArr[i]) {
							hiddenWord[i] = playerGuess;
							count++;

						}
					}

					toShow = GameMenu.printHiddenWord(hiddenWord);
//					System.out.println(Arrays.toString(toShow));
					// if the users input did not return any matches, this is considered a failed
					// attempt
					if (count == 0) {
						GameMenu.printHangman(attempts, word);
						System.out.printf("attempts %s \n", attempts);
						// reset the count for the next round.
						count = 0;
					}

					attempts++;

					// System.out.println(String.join(" ",toShow));

				} else {
					System.out.println("Select a valid option. ");
					System.out.println("(W) to guess the word. ");
					System.out.println("(L) to guess a letter. ");

				}
				// If the input word (by letters) matches the hidden word, the player has won.
				if (String.join("", toShow).equals(word)) {

					System.out.println("Nice! you guessed the word ! \n");
					playAgain = GameMenu.playAgainMenu();

				}

			} else {
				// Player out of lives,
				System.out.println("You ran out of lives!");
				GameMenu.printHangman(attempts, word);
				playAgain = GameMenu.playAgainMenu();

			}

		}

	}

}
