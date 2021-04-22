package Program;

import Computer.Word;

public class Mul extends BinaryExpr{
	
	private final String opName = "MUL";

    protected Mul(Operand o1, Operand o2, Operand address) {
        super(o1, o2, address, "MUL");
    }

    @Override
    protected Word compute(Word o1, Word o2) {
        Word newWord = o1.mul(o2);
		return newWord;	
    }

}