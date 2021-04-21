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
	
	public abstract boolean eq(Word w1);
	
	public abstract void add(Word w1, Word w2);
	
	public abstract void mul(Word w);
	
	public int readOperand(Memory m) {
		return m[0];
	}

}
