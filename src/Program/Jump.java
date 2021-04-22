package Program;

import Computer.Memory;
import Computer.PC;

public class Jump implements Instruction {
	
	private final String opName = "JMP";
	private int dest;
	private Operand op;
	
	public Jump(int dest, Operand op) {
		this.dest = dest;
		this.op = op;
		
	}
	
	@Override
	public void execute(Memory mem, PC pc) {
		pc.setPC(pc.getPC() + dest);
		
	}

	@Override
	public String toString() {
		return opName + " " + op;
	}

	
}
