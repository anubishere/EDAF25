package Computer;

public abstract class Word {
	
	public abstract int getValue(Word w);
	
	public abstract void setValue(Word newWord);
	
	public abstract String toString();
	
	public abstract boolean eq(Word w);
	
	public abstract Word add(Word w);
	
	public abstract Word mul(Word w);

	public abstract Word copy();

	public abstract long getWord();
	


}