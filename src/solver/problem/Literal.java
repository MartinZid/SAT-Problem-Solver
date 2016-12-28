package solver.problem;

/**
 * Literal a.k.a. variable.
 * @author Martin
 */
public class Literal {

    private final int id;
    private final int value;
    // false sign represents negation
    private final boolean sign;
    
    public Literal(int id, int value, int sign)
    {
        this.id = id;
        this.value = value;
        this.sign = sign > 0;
    }
    
    @Override
    public String toString()
    {
        String literal = sign? "+": "-";
        return literal += String.valueOf(value);
    }

    public int getId()
    {
        return id;
    }

    public int getValue()
    {
        return value;
    }

    public boolean getSign()
    {
        return sign;
    }
    

}
