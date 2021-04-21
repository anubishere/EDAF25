package Program;

import Computer.Memory;
import Computer.PC;

public interface Instruction {
	
	public abstract void execute(Memory mem, PC pc);
	public abstract String toString();
}
