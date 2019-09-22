import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayerGuess extends General {
	
	public void genAns() {
		String[] names = super.getNames();
		ArrayList<String> ans = new ArrayList<>();
		Random rand = new Random();
		while (ans.size()<super.size) {
			int r = rand.nextInt(names.length);
			String candidate = names[r];
			if (!ans.contains(candidate))
				ans.add(candidate);
		}
		setAnswer(ans);
		System.out.println();
	}
	
	public void printAns() {
		System.out.println("\nToo bad! The answer was:");
		ArrayList<Colour> ans = getAns();
		for (Colour c : ans) {
			System.out.print(c.getName()+" ");
		}
		System.out.println("\n");
	}
	
	public boolean containsColour(String guess_name) {
		ArrayList<String> names = new ArrayList<>();
		for (Colour c : super.getAns()) {
			String name = c.getName();
			names.add(name);
		}
		return names.contains(guess_name);
	}
	
	public void setHint() {
		for (int i=0;i<size;i++) {
			Colour ans_c = super.getAns().get(i);
			Colour guess_c = guess.get(i);
			String ans_name = ans_c.getName();
			String guess_name = guess_c.getName();
			if (ans_name.equals(guess_name)) {
				hints.add(2);
			} else if (containsColour(guess_name)) {
				hints.add(1);
			} 
		}
		Collections.shuffle(hints);
	}
	
	public void checkReveal(ArrayList<String> input) {
		if (input.size()==1) {
			String item = input.get(0);
			char[] item_char = item.toCharArray();
			char check = item_char[0];
			if (Character.isDigit(check) && Character.getNumericValue(check)==0) {
				printAns();
				System.out.println("Come again soon!");
				System.exit(0);
			}		
		}
	}
	
	public void checkWin() {
		boolean check = !(super.hints.contains(1))&&(super.hints.size()==super.size);
		if (check) {
			System.out.println("Congrats! You cracked the code.");
			System.exit(0);
		} 
	}
	
	public void mainAlgorithm() {
		super.addGuess();
		System.out.println("\nYour guesses so far:\n");
		setHint();
		super.addHint();
		super.printGuesses();
		super.printHint();
		checkWin();
		super.clearGuess();
		super.clearHint();
	}
}
