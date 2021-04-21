package Computer;

import Program.Address;

public abstract class Memory {
	private Word[] memory;
	
	public Memory(int length) {
		this.memory = new Word[length];
	}
	
	public Word read(Address a) {
		return this.memory[a.getIndex()];
	}

}
