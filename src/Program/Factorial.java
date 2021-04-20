package Program;

import Computer.LongWord;

public class Factorial extends Program{
    public Factorial(){
        Address n = new Address(0), fac = new Address(1);
        add(new Copy(new LongWord(5), n));
        add(new Copy(new LongWord(5), fac));
        add(new JumpEq(6, n, new LongWord(1)));
        add(new Mul(fac, n, fac));
        add(new Jump(2));
        add(new Print(fac));
        add(new Halt());
    }
}