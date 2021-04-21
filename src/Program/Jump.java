package Program;

import Computer.Memory;
import Computer.PC;

public class Jump extends Instruction {
	
	private final String opName;
	private int dest;
	private Operand op;
	
	public Jump(String opName, int dest, Operand op) {
		this.opName = opName;
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
