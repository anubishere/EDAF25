package Program;

import Computer.Memory;
import Computer.Word;

public interface Operand {
	
	public Word readOperand(Memory mem);
	public String toString();
}
