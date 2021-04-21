package Program;

import Computer.Memory;
import Computer.Word;

public abstract class BinaryExpr extends Instruction  {
	
	private Operand o1;
	private Operand o2;
	private Address address;
	private String opName;
	
	protected BinaryExpr(Operand o1, Operand o2, Address address, String opName) {
		this.o1 = o1;
		this.o2 = o2;
		this.address = address;
		this.opName = opName;
	}
	
	
	protected abstract Word compute(Word o1, Word o2);
	
	public int run() {
		
	}
	

}
