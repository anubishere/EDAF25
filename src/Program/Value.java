package Program;

import Computer.Memory;
import Computer.Word;

public class Value implements Operand{
private int number;

	public Value(int number) {
		this.number = number;
	}
	
	//TODO
	public Word getWord(Memory mem) {
		return null;
	}
}
