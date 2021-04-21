package Program;


import Computer.Word;

public class Add extends BinaryExpr {
	
	public Add(Operand o1, Operand o2, Operand address) {
		super(o1, o2, address); // attributen ska ärva från BinaryExpr helt och hållet
	}
	
	/* Metoden i BinaryExpr skuggas */
	
	@Override
	protected Word compute(Word o1, Word o2) {
		Word newWord = o1.add(o2);
		return newWord;	
	}

}
