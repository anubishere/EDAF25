package Computer;

public abstract class Word extends Memory {
	private int Word;
	
	
	public Word() {
		
	}
	
	/* Ska returnera ord från listan av ord som finns i minnet */
	
	public Word getWord(Memory m) {
		return m.getWord();
	}
	
	public abstract String toString();
	
	public abstract boolean eq(Word w);
	
	public abstract Word add(Word w);
	
	public abstract Word mul(Word w);

	public abstract Word copy();

	public abstract long getWord();
	
	public int readOperand(Memory m) {
		return m[0];
	}

}