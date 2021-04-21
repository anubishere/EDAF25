package Computer;

public class LongWord implements Word{
    private long word;

    public LongWord(long word){
        this.word = word;
    }

    @Override
    public Word add(Word w) {
        return new LongWord((w).getWordValue() + this.word);
    }

    @Override
    public Word mul(Word w) {
        return new LongWord((w).getWordValue() * this.word);
    }

    @Override
    public boolean eq(Word w) {
        return this.word == w1.getWordValue();
    }

    @Override
    public Word copy() {
        return new LongWord(word);
    }

    @Override
    public String toString() {
        return Long.toString(word);
    }

    @Override
    public long getWordValue() {
        return word;
    }

	@Override
	public void setWordValue(Word value) {
		this.word = value.getWordValue();
		
	}

	@Override
	public Word readOperand(Memory mem) {
		return this;
	}

}