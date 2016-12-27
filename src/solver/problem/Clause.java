package solver.problem;

import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class Clause {
    
    private ArrayList<Literal> literals = new ArrayList<>();

    public void addLiteral(Literal literal)
    {
        literals.add(literal);
    }
    
    public String toString()
    {
        String clause = "";
        for(Literal l: literals)
        {
            clause += " " + l.toString() + " ";
        }
        return clause;
    }

}
