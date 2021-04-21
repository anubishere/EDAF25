package Computer;

public class PC {
	
	private int programCounter;
	
	public PC() {
		programCounter = 0;
	}
	
	public int getPC() {
		return programCounter;
	}
	
	public void setPC(int next) {
		programCounter = next;
	}

}
