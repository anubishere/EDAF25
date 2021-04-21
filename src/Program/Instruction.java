package Program;

import Computer.Memory;
import Computer.Word;

public abstract class Instruction {
	
	public Instruction() {
		
	}
	
	
	
	public String toString() {
		return null;
		
	}
	
	public abstract void Execute(Memory mem);
	

	

}
