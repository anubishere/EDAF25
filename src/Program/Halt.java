package Program;

import Computer.Memory;
import Computer.PC;

public class Halt implements Instruction {

    @Override
    public void execute(Memory mem, PC pc) {
        pc.setPC(-1);
    }
    @Override
    public String toString() {
		return "opName";
	}
}
