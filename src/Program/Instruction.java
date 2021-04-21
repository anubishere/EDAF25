package Program;

import Computer.Memory;
import Computer.PC;
import Computer.Word;

public abstract class Instruction {
	
	
	public abstract String toString();


	public abstract void execute(Memory mem, PC pc);


	

}
