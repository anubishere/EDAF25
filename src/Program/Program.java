package Program;

import java.util.ArrayList;

import Computer.PC;

public abstract class Program extends ArrayList<Instruction>{
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		
		for(Instruction inst : this) {
			sb.append(i + ": " + inst.toString() + "\n");
			i++;
		}
		return sb.toString();
	}

}

