package Computer;

import Program.Operand;

public interface Word extends Operand {
	
	public abstract boolean eq(Word w);
	
	public abstract Word add(Word w);

	public abstract Word mul(Word w);

	public abstract Word copy();

	public abstract long getWordValue();

	public abstract void setWordValue(Word value);
	
	public abstract String toString();

}