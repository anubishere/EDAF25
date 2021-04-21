package Program;

import Computer.Memory;
import Computer.PC;

public class Print extends Instruction {
	
	private String opName;
	private Operand op;
	
	public Print(String opName, Operand op) {
		this.opName = opName;
		this.op = op;
		
	}
	
	@Override
	public void execute(Memory mem, PC pc) {
		System.out.println(op.readOperand(mem));
		pc.setPC(pc.getPC() + 1);
		
	}

	@Override
	public String toString() {
		return opName + " " + op;
		
	}

}
