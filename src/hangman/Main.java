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
	public static String  newWord(File wordList) throws FileNotFoundException {
		Scanner textScanner = new Scanner(wordList);
		ArrayList <String> words = new ArrayList<>();
		
		while(textScanner.hasNextLine()) {
			words.add(textScanner.nextLine());
		}
		String word = words.get((int)(Math.random()*words.size()));
		System.out.println(word);
		
		return word;
		

	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		int attempts =1;
		boolean activeGame = false;
		boolean playAgain = false;
		Scanner s = new Scanner(System.in);

		File wordList = new File ("/Users/param/Desktop/hangman/src/hangman/wordlist.txt");
		
		 String word = newWord(wordList);
		
		//Convert our word into a character array
			char[] wordArr = word.toCharArray();
			//Create an array of underscores that will be shown to the user,
			//this is the same size as the word. 
			char[] hiddenWord = new char[wordArr.length];
			//fill the array with underscores
			Arrays.fill(hiddenWord, '_');
			
			System.out.printf("hidden %s" ,Arrays.toString(hiddenWord));
			for (char c:wordArr) {
				System.out.println(c);
				
				}
			String[] toShow = GameMenu.printHiddenWord(hiddenWord);
		while(!activeGame) {
			GameMenu.printWelcome();
			activeGame=true;
			
		}
		
		while(activeGame) {
			if(playAgain) {
				System.out.println("play again");
				attempts = 0;
				word = newWord(wordList);
				//Convert our word into a character array
				wordArr = word.toCharArray();
				//Create an array of underscores that will be shown to the user,
				//this is the same size as the word. 
				hiddenWord = new char[wordArr.length];
				//fill the array with underscores
				Arrays.fill(hiddenWord, '_');
				toShow = GameMenu.printHiddenWord(hiddenWord);
				playAgain = false;
			}
			if(!playAgain && attempts <8) {
//				System.out.println(Arrays.toString(hiddenWord));
				
				System.out.printf("%s \n",String.join(" ",toShow));
				System.out.printf("Would you like to guess a letter (L) OR a word (w) \n");
				char guessType = s.next().charAt(0) ;
				
				if(guessType == 'w' || guessType == 'W') {
					System.out.println("Enter a Word to guess.");
					String wordGuess = s.next();
					
					if(wordGuess.equals(word)) {
						
						System.out.println("Nice! you guessed the word ! \n");
						System.out.println("Play again ? Y/N");
						char playAgainOption = s.next().charAt(0);
						
						
						if(playAgainOption == 'y' ||playAgainOption == 'Y') {
							playAgain = true;
						}else if((playAgainOption == 'n' ||playAgainOption == 'N')) {
							
							System.out.println("Goodbye!");
							return;
						}else {
							System.out.println("Invalid input!");
							System.out.println("Play again ? Y/N");
							 playAgainOption = s.next().charAt(0);
						}
						
					}else {
						
						attempts++;
						GameMenu.printHangman(attempts,word);
						System.out.printf("attempts %s \n", attempts);
						
					}
					
				
				}else if(guessType == 'l'||guessType == 'L') {
					System.out.println("Enter a letter to guess.");
					System.out.printf("You have %s attempts remaining \n",8-attempts);
					char playerGuess = s.next().charAt(0) ;
					
					Integer count =0;
					for(int i =0; i<wordArr.length;i++) {
						
						if(playerGuess == wordArr[i]) {
							hiddenWord[i]=playerGuess;
							count++;
							
						}
					}
					
					toShow = GameMenu.printHiddenWord(hiddenWord);
//					System.out.println(Arrays.toString(toShow));

					if(count ==0) {
						GameMenu.printHangman(attempts,word);
						System.out.printf("attempts %s \n", attempts);
						count =0;
					}
					
					attempts++;

					//System.out.println(String.join(" ",toShow));
					
				}else {
					System.out.println("Select a valid option. ");
					System.out.println("(W) to guess the word. ");
					System.out.println("(L) to guess a letter. ");
					
				}
						
				if(String.join("", toShow).equals(word)) {
					
					System.out.println("Nice! you guessed the word ! \n");
					playAgain = GameMenu.playAgainMenu();
					
					}
				
			}else {
				
				System.out.println("You ran out of lives!");
				GameMenu.printHangman(attempts,word);
				playAgain = GameMenu.playAgainMenu();
				
			}
			
		}
		
	}
	
	
		
}
