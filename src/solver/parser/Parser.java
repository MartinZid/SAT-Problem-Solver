package solver.parser;

import solver.problem.Literal;
import java.util.ArrayList;
import solver.problem.Clause;
import solver.problem.Problem;

/**
 *
 * @author Martin
 */
public class Parser {

    private boolean parsedWeights = false;
    private ArrayList<Integer> weights = new ArrayList<>();
    private Problem problem = new Problem();
    
    public void parseLine(String line)
    {        
        if(!parsedWeights)
            parseWeights(line);
        else
            parseClause(line);
    }

    private void parseWeights(String line)
    {
        String [] data = line.split(" ");
        if(data[0].equals("p"))
            return;
        for(String s: data)
        {
            weights.add(Integer.parseInt(s));
        }
        parsedWeights = true;
    }

    private void parseClause(String line)
    {
        String [] data = line.split(" ");
        Clause c  = new Clause();
        for(int i = 0; i < data.length - 1; i++) // last one is always zero
        {
            int id = Integer.parseInt(data[i]);
            int sign = id/Math.abs(id);
            c.addLiteral(new Literal(weights.get(Math.abs(id)-1), sign));
        }
        problem.addClause(c);
    }
    
    public Problem getProblem()
    {
        return problem;
    }
}
