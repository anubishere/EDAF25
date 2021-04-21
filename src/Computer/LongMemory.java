package Computer;

public class LongMemory extends Memory{

    public LongMemory(int size) {
        super(size);
    }
    
    protected void populate(int size) {
    	for(int i = 0; i < size; i++) {
    		this.memory[i] = new LongWord(0);
    	}
    }
}
