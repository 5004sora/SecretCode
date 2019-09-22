import java.util.ArrayList;
import java.util.Random;

public class AIGuess extends General {
	
	public void genFirstAns() {
		String[] names = super.getNames();
		ArrayList<String> g = new ArrayList<>();
		Random rand = new Random();
		while (g.size()<super.size) {
			int r = rand.nextInt(names.length);
			String candidate = names[r];
			if (!g.contains(candidate))
				g.add(candidate);
		}
		super.setGuess(g);
	}
	
	public void setPos() {
		//TBC.
	}
	
	public void solve() {
		//TBC.
	}
	
	public void printGuess() {
		System.out.println("Computer guesses:");
		for (Colour c : super.guess) {
			System.out.print(c.getName()+" ");
		}
		super.clearGuess();
		System.out.println();
	}
	
	public void printAns() {
		System.out.println();
		System.out.println("Your code is:");
		for (Colour c : super.getAns()) {
			System.out.print(c.getName()+" ");
		}
		System.out.println("\n");
	}
	
	public void printEnd() {
		System.out.println("The computer couldn't crack the code. Good job!");
	}
	
	public boolean checkValidHint(ArrayList<String> hint) {
		int count = 0;
		ArrayList<String> check = new ArrayList<>();
		for (String h : hint) {
			if (h.equals("1")||h.equals("2")) {
				count++;
				check.add(h);
			} else {
				check.add(h);
			}
		}
		if (check.size()==count&&hint.size()>0&&hint.size()<=4) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setHint(ArrayList<String> hint) { 
		if (checkValidHint(hint)) {
				for (String h : hint) {
					super.hints.add(Integer.parseInt(h));
				}
			} else {
			System.out.println("\nInvalid inputs - hint must be made up of 1s and 2s only.");
		}
	}
	
	public void checkExit(ArrayList<String> input) {
		if (input.size()==1) {
			String item = input.get(0);
			char[] item_char = item.toCharArray();
			char check = item_char[0];
			if (Character.isDigit(check) && Character.getNumericValue(check)==0) {
				System.out.println("Come again soon!");
				System.exit(0);
			}
		}
	}
	
	public void checkWin() {
		boolean check = !(super.hints.contains(1));
		if (check && super.hints.size()==4) {
			System.out.println("The computer cracked the code!");
			System.exit(0);
		}
	}
	
	public void mainAlgorithm() {
		System.out.println("\nThe computer's guesses so far:\n");
		super.addHint();
		super.printGuesses();
		super.printHint();
		checkWin();
		super.clearGuess();
		super.clearHint();
	}
}
