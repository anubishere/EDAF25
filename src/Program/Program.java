package Program;

import java.util.ArrayList;

import Computer.PC;

public abstract class Program extends ArrayList<Instruction>{
	
	
	public Program() {
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		
		for(Instruction inst : this) {
			sb.append(i + ": " + inst.toString() + "\n");
			i++;
		}
		sb.append("\n");
		return sb.toString();
	}

}
