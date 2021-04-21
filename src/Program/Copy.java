package Program;

import Computer.Memory;
import Computer.PC;

public class Copy implements Instruction {

	private final String opName;
	private Operand op;
	private Address address;

	public Copy(Operand op, Address address) {
		this.opName = "CPY";
		this.op = op;
		this.address = address;
	}

	@Override
	public void execute(Memory mem, PC pc) {
		mem.setWord(op.readOperand(mem), address);
		pc.setPC(pc.getPC() + 1);

	}

	@Override
	public String toString() {
		return opName + " " + op + " " + address;
	}

}