package Program;

import Computer.Memory;
import Computer.PC;

public class JumpEq implements Instruction {

	private final String opName = "JEQ";
	private int dest;
	private Operand op1;
	private Operand op2;

	public JumpEq(int dest, Operand op1, Operand op2) {
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;

	}

	@Override
	public void execute(Memory mem, PC pc) {
		if(op1.readOperand(mem).eq(op2.readOperand(mem))) {
			pc.setPC(dest);
		} else {
			pc.setPC(pc.getPC() + 1);
		}

	}

	@Override
	public String toString() {
		return this.opName + " " + this.dest + " " + op1 + " " + op2;
	} 
}
