package solver.problem;

/**
 *
 * @author Martin
 */
public class Literal {

    private final int value;
    private final boolean sign;
    
    public Literal(Integer value, int sign)
    {
        this.value = value;
        this.sign = sign > 0;
    }
    
    @Override
    public String toString()
    {
        String literal = sign? "+": "-";
        return literal += String.valueOf(value);
    }

}
