package Program;

import Computer.Word;

public class Add implements BinaryExpr {
	
	
	
	public Add(Operand o1, Operand o2, Address address, String opName) {
		super(o1, o2, address, opName); // attributen ska ärva från BinaryExpr helt och hållet
	}
	
	/* Metoden i BinaryExpr skuggas */
	
	@Override 
	protected Word compute(Word o1, Word o2) {
		return o1.add(o2);
	}
}
