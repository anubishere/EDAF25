package Computer;

import Program.Program;

public class Computer {

    private Memory memory;
    private Program program;
    private PC pc;

	public Computer(Memory memory) {
        this.memory = memory;
    }
    public void load(Program program){
        this.program = program;
    }
    public void run(){
        while(pc.getPC() >= 0){
            program.get(pc.getPC()).Execute(memory, pc);
        }
    }
}