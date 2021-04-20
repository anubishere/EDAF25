package Computer;

public abstract class Memory {
	private Word[] memory;
	
	public Memory(int length) {
		this.memory = new Word[length];
	}

}
