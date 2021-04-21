package Program;

import Computer.Memory;
import Computer.PC;

public class Halt extends Instruction {
	
	private String opName;
	
	public Halt(String opName) {
		this.opName = opName;
		
	}

	@Override
	public void execute(Memory mem, PC pc) {
		pc.setPC(-1);
		
	}

	@Override
	public String toString() {
		return opName;
		
	}


}
