package Program;

import Computer.Memory;
import Computer.PC;

public class Copy implements Instruction {

	private final String opName = "CPY";
	private Operand op;
	private Address address;

	public Copy(Operand op, Address address) {
		this.op = op;
		this.address = address;
	}

	@Override
	public void execute(Memory mem, PC pc) {
		mem.setWord(op.readOperand(mem), address);
		pc.incrementPC();
	}

	@Override
	public String toString() {
		return opName + " " + op + " " + address;
	}
}