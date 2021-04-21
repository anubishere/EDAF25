package Program;

import Computer.Word;

public class Mul extends BinaryExpr {
	
	public Mul(Operand o1, Operand o2, Address address, String opName) {
		super(o1, o2, address);
		
	}

	@Override
	protected Word compute(Word o1, Word o2) {
		Word newWord = o1.mul(o2);
		
		return newWord;
	}

	@Override
	public String toString() {
		return o1 + " " + o2 + " " + address;
	}


}