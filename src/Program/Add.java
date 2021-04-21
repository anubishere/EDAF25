package Program;

import Computer.Word;

public class Add extends BinaryExpr {
	
	
	
	public Add(Operand o1, Operand o2, Address address, String opName) {
		super(o1, o2, address); // attributen ska ärva från BinaryExpr helt och hållet
	}
	
	/* Metoden i BinaryExpr skuggas */
	
	protected Word compute(Word o1, Word o2) {
		return add(o1, o2);
	}
	

}
