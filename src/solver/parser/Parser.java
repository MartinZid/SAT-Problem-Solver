package solver.parser;

import solver.problem.Literal;
import java.util.ArrayList;
import solver.problem.Clause;
import solver.problem.Problem;

/**
 * Parses file and creates problem with clauses and literals.
 * @author Martin
 */
public class Parser {

    private boolean parsedWeights = false;
    private ArrayList<Integer> weights = new ArrayList<>();
    private Problem problem;
    
    /**
     * Passes line to appropriate method.
     * @param line 
     */
    public void parseLine(String line)
    {        
        if(!parsedWeights) // we read weights
            parseWeights(line);
        else // we read one clause
            parseClause(line);
    }

    /**
     * Creates array with weights.
     * @param line 
     */
    private void parseWeights(String line)
    {
        String [] data = line.split(" ");
        if(data[0].equals("p")) // Metadata
            return;
        for(String s: data)
        {
            weights.add(Integer.parseInt(s));
        }
        problem = new Problem(weights);
        parsedWeights = true;
    }

    /**
     * Creates clauses with parsed literals.
     * @param line 
     */
    private void parseClause(String line)
    {
        String [] data = line.split(" ");
        Clause c  = new Clause();
        for(int i = 0; i < data.length - 1; i++) // last one is always zero
        {
            int id = Integer.parseInt(data[i]);
            int value = weights.get(Math.abs(id)-1);
            int sign = id/Math.abs(id);
            c.addLiteral(new Literal(id*sign, value, sign));
        }
        problem.addClause(c);
    }
    
    /**
     * Returns created problem.
     * @return 
     */
    public Problem getProblem()
    {
        return problem;
    }
}
