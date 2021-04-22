package Program;

import Computer.Memory;
import Computer.Word;

public class Address implements Operand{
	private int address;
	
	public Address(int address) {
		this.address = address;
	}
	
	public int getAddress() {
		return this.address;
	}

	@Override
	public Word readOperand(Memory mem) {
		return mem.getWord(this);
	}

	@Override
	public String toString(){
		return "[" + address + "]";
	}
	
}