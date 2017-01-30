package solver.problem;

import java.util.ArrayList;

/**
 * Clause with OR(+) literal.
 * @author Martin
 */
public class Clause {
    
    private ArrayList<Literal> literals = new ArrayList<>();

    public void addLiteral(Literal literal)
    {
        literals.add(literal);
    }
    
    public boolean isTRUE(ArrayList<Boolean> configuration)
    {
        for(Literal l: literals)
        {
            int id = l.getId()-1;
            // configration and sign XOR
            if(!(configuration.get(id) ^ l.getSign())) 
                return true; // for x + y + z + ... to be TRUE is only one TRUE enough
        }
        return false;
    }
    
    @Override
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
