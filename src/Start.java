import Computer.Computer;
import Computer.LongMemory;
import Program.Program;

public class Start {
    public static void main(String[] args) throws Exception {
        Program factorial = new Factorial();
        System.out.println(factorial);
        Computer computer = new Computer(new LongMemory(1024));
        computer.load(factorial);
        computer.run();
        
    }
}
