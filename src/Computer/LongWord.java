package Computer;

public class LongWord implements Word{
    private long word;

    public LongWord(long word){
        this.word = word;
    }

    @Override
    public Word add(Word w) {
        return new LongWord((w).getWord() + this.word);
    }

    @Override
    public Word mul(Word w) {
        return new LongWord((w).getWord() * this.word);
    }

    @Override
    public boolean eq(Word w1) {
        return this.word == w1.getWord();
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
    public long getWord() {
        return word;
    }

	@Override
	public void setValue(Word newWord) {
		this.word = newWord.getWord();
		
	}

	@Override
	public Word readOperand(Memory mem) {
		return this;
	}

}