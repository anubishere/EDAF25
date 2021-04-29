package Program;

import Computer.Memory;
import Computer.PC;

public interface Instruction {
	
	public void execute(Memory mem, PC pc);
	public String toString();
}
