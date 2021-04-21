package Program;

import Computer.Memory;
import Computer.PC;
import Computer.Word;

public abstract class Instruction {
	
	
	public abstract void Execute(Memory mem, PC pc);
	

	public abstract String toString();

}
