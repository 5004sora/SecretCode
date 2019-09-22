import java.util.ArrayList;
import java.util.Scanner;

public class SecretCode {
	
	public static void printMenu() {
		String s = "\nWelcome!"
				+ "\nWould you like to:" 
				+ "\nAsk (Press 1)"
				+"\nGuess (Press 2)"
				+"\nRules (Press 3)"
				+"\nExit (Press 4)"
				+"\nYour input:";
		System.out.println(s);
	}
	
	public static void printRules() {
		String ask_rules = "\nHow to play:\n" 
				+ "\nAsking (UNDER DEVELOPMENT): Type in your secret code composed of four colours (with no duplicates), and let the computer guess it for you!" 
				+"\nYou'll have to give hints along the way. 1 is for a correct colour that is not at the right position,"
				+"\nwhile 2 is for a correct colour that is in the right position!";
		String guess_rules = "\n\nGuessing: The computer will generate a secret code made up of four unique colours, and you'll have to guess it!"
				+"\nWhen you make a guess, the computer will give you hints to help you crack the code."
				+ "\nA 1 is given when a colour is correct but is not at the right position, "
				+ "\nand 2 is given when a colour is both correct and at the right position.";
		String rules = ask_rules += guess_rules;
		System.out.println(rules);
	}
	
	public static String askInitInput() {
		@SuppressWarnings("resource")
		Scanner sc1 = new Scanner(System.in);
		String in = sc1.next();
		return in;
	}
	
	public static ArrayList<String> askInput() {
		@SuppressWarnings("resource")
		Scanner sc2 = new Scanner(System.in);
		String inp = sc2.nextLine();
		String[] inp_split = inp.split(" ");
		ArrayList<String> in = new ArrayList<>();
		for (String s : inp_split) {
			in.add(s);
		}
		return in;
	}
	
	public static void main(String[] args) {
		boolean t = true;
		int turn = 1;
		AIGuess ai_guess = new AIGuess();
		PlayerGuess p_guess = new PlayerGuess();
		printMenu();
		String inp = askInitInput();
		if (inp.equals("1")) {
			System.out.println("\nAsking...\n\nPress 0 to quit anytime!\n");
			while (t) {
				System.out.println("Type in your code. Pick four colours from red, blue, yellow, orange, white, pink, green, and purple:");
				ArrayList<String> inputs = askInput();
				ai_guess.checkExit(inputs);
				ai_guess.setAnswer(inputs);
				if (ai_guess.getAns().size() != 0)
					break;
			}			
			ai_guess.printAns();
			while (turn<11) {
				ai_guess.genFirstAns();
				ai_guess.addGuess();
				ai_guess.printGuess();
				while (t) {
					System.out.println("\nYour hint?");
					ArrayList<String> inputs = askInput();
					ai_guess.checkExit(inputs);
					ai_guess.setHint(inputs);
					if (ai_guess.hints.size()!=0)
						break;
				}
				ai_guess.mainAlgorithm();
				turn++;
			}
			ai_guess.printEnd();
		} else if (inp.equals("2")) {
			System.out.println("\nGuessing...\n\nPress 0 to quit anytime and reveal the answer!");
			p_guess.genAns();
			while (t) {
				System.out.println("Turn 1:");
				System.out.println("Your guess? Pick four colours from red, blue, yellow, orange, white, pink, green, and purple.");
				ArrayList<String> first_inputs = askInput();
				p_guess.checkReveal(first_inputs);
				p_guess.setGuess(first_inputs);
				if (p_guess.guess.size() !=0)
					break;
			}
			p_guess.mainAlgorithm();
			while (turn<10) {
				while (t) {
					System.out.println("Turn: "+(turn+1));
					System.out.println("Your guess?");
					ArrayList<String> inputs = askInput();
					p_guess.checkReveal(inputs);
					p_guess.setGuess(inputs);
					if (p_guess.guess.size() != 0)
						break;
					}
				p_guess.mainAlgorithm();
				turn++;
				
			}
			p_guess.printAns();
		} else if (inp.equals("3")) {
			printRules();
			main(args);
		} else if (inp.equals("4")) {
			System.out.println("Come again soon!");
			System.exit(0);
		}
		else {
			System.out.println("\nInvalid input. Try again!");
			main(args);
		}
	}
}
