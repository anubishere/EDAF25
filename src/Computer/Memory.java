package Computer;

import Program.Address;

public abstract class Memory {
	private Word[] memory;
	
	public Memory(int length) {
		this.memory = new Word[length];
	}
	
	public Word getWord(Address a) {
		return this.memory[a.getIndex()];
	}
	
	public void setWord(Word input, Address address) {
		this.memory[address.getIndex()] = input;
	}

}
