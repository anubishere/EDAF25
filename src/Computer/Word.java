package Computer;

public abstract class Word {
	
	public abstract int getValue(Word w);
	
	public abstract void setValue(Word newWord);
	
	public abstract String toString();
	
	public abstract boolean eq(Word w1);
	
	public abstract Word add(Word w2);
	
	public abstract void mul(Word w);


}
