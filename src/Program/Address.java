package Program;

import Computer.Memory;
import Computer.Word;

public class Address implements Operand{
	private int index;
	
	public Address(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public Word getWord(Memory mem) {
		return mem.getWord(this);
	}
	
	
}
