package Program;

import Computer.Memory;
import Computer.PC;

public class Jump implements Instruction {
	
	private final String opName = "JMP";
	private int dest;
	
	public Jump(int dest) {
		this.dest = dest;
	}
	
	@Override
	public void execute(Memory mem, PC pc) {
		pc.setPC(dest);
	}

	@Override
	public String toString() {
		return opName + " " + this.dest;
	}
}
