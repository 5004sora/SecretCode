public class Colour {
	
	protected int score=0;
	private int pos;
	private final String name;
	
		
	public Colour(String s) {
		this.name=s.toLowerCase();
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int s) {
		this.score = s;
	}
	
	public int getPos() {
		return pos;
	}
	
	public void setPos(int p) {
		this.pos = p;
	}
}
