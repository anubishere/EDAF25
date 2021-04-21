package Program;

import Computer.Memory;
import Computer.PC;

public class JumpEq extends Instruction {

	private final String opName;
	private int dest;
	private Operand op1;
	private Operand op2;

	public JumpEq(String opName, int dest, Operand op1, Operand op2) {
		this.opName = opName;
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;

	}

	@Override
	public void Execute(Memory mem, PC pc) {
		if(op1.readOperand(mem).equals(op2.readOperand(mem))) {
			pc.setPC(pc.getPC() + dest);
		}

	}

	@Override
	public String toString() {
		return opName + " " + op1 + " " + op2;
	} 
}
