import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class General {
	
	private final String[] names = {"red","blue","purple","white","green","yellow","orange","pink"};
	protected final int size=4;
	private ArrayList<Colour> answer = new ArrayList<>(size);
	protected ArrayList<Colour> guess = new ArrayList<>();
	private ArrayList<ArrayList<Colour>> past_guesses = new ArrayList<>();
	protected ArrayList<Integer> hints = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> past_hints = new ArrayList<>();
	
	public void setGuess(ArrayList<String> code) {
		if (code.size()==4 && checkInput(code) && noDups(code)) {
			for (String s : code) {
				guess.add(new Colour(s));
			}
		} else {
			System.out.println("\nInvalid inputs - Colours must be whole words and there can be no duplicates. Try again!\n");
		}		
	}
	
	public void printGuesses() {
		for (int i=0;i<past_guesses.size();i++) {
			System.out.print("Turn "+(i+1)+":  ");
			ArrayList<Colour> arr = past_guesses.get(i);
			for (Colour c : arr) {
				System.out.print(c.getName()+"  ");
			}
			System.out.print(" | ");
			if (hints.size() != 0) {
				ArrayList<Integer> curr_hint = past_hints.get(i);
				for (Integer n : curr_hint) {
					System.out.print(n+" ");
				}
			} else {
				System.out.print("N/A");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public ArrayList<Colour> copyGuess() {
		ArrayList<Colour> copy = new ArrayList<>();
		for (Colour c : guess) {
			copy.add(c);
		}
		return copy;
	}
	
	public void addGuess() {
		past_guesses.add(copyGuess());
	}
	
	public void clearGuess() {
		guess.clear();
	}
	
	public void printHint() {
		System.out.print("Hint: ");
		for (Integer hint : hints) {
			System.out.print(hint+" ");
		}
		System.out.println("\n");
	}
	
	public ArrayList<Integer> copyHint() {
		ArrayList<Integer> copy = new ArrayList<Integer>();
		for (Integer hint : hints) {
			copy.add(hint);
		}
		return copy;
	}
	
	public void addHint() {
		past_hints.add(copyHint());
	}
	
	public void clearHint() {
		hints.clear();
	}
	
	public boolean checkInput(ArrayList<String> code) {
		ArrayList<Boolean> bs = new ArrayList<Boolean>(code.size());
		for (int i=0;i<code.size();i++) {
			bs.add(Arrays.asList(names).contains(code.get(i).toLowerCase()));
		}
		return !(bs.contains(false));
	}
	
	public boolean noDups(ArrayList<String> code) {
		HashMap<String,Integer> map = new HashMap<>();
		for (String s : code) {
			if (!map.containsKey(s)) {
				map.put(s.toLowerCase(),1);
			} else {
				int count = map.get(s);
				count++;
				map.replace(s,count);
			}
		}
		for (Integer count : map.values()) {
			if (count.intValue()>1) 
				return false;
		}
		return true;
	}
	
	public void setAnswer(ArrayList<String> code) {
		if (code.size()==4 && checkInput(code) && noDups(code)) {
			for (int i=0;i<code.size();i++) {
				answer.add(new Colour(code.get(i)));
			}
		} else {
			System.out.println("\nInvalid inputs - Colours must be whole words and there can be no duplicates. Try again!\n");
		}
	}
	
	public String[] getNames() {
		return names;
	}
	
	public ArrayList<Colour> getAns() {
		return answer;
	}
}
