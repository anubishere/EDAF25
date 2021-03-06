package Program;

import Computer.Memory;
import Computer.PC;
import Computer.Word;

public abstract class BinaryExpr implements Instruction  {
	
	protected Operand o1;
	protected Operand o2;
	protected Operand address;
	private final String opName;
	
	protected BinaryExpr(Operand o1, Operand o2, Operand address, String opname) {
		this.o1 = o1;
		this.o2 = o2;
		this.address = address;
		this.opName = opname;
	}
	
	protected abstract Word compute(Word o1, Word o2);
	
	/* Result = resultat efter uträkning
	 * wordOnAddress är addressen vi skriver till
	 */
	@Override
	public void execute(Memory mem, PC pc) {
		Word result = compute(o1.readOperand(mem), o2.readOperand(mem));
		Word wordOnAddress = address.readOperand(mem);
		wordOnAddress.setWordValue(result);
		pc.incrementPC();
	}
	
	public String toString(){
		return opName + " " + this.o1 + " " + this.o2 + " " + this.address;
	}
}
