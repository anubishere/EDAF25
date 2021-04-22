package Program;

import Computer.Memory;
import Computer.PC;

public class Print implements Instruction {
	
	private final String opName = "PRT";
	private Operand op;
	
	public Print(Operand op) {
		this.op = op;
	}
	
	@Override
	public void execute(Memory mem, PC pc) {
		System.out.println(op.readOperand(mem));
		pc.setPC(pc.getPC() + 1);
		
	}

	@Override
	public String toString() {
		return opName + " " + op.toString();
		
	}

}
