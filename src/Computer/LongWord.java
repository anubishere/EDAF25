package Computer;

public class LongWord extends Word{
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
    public String toString() {
        return null;
    }

    @Override
    public long getWord() {
        return word;
    }
}