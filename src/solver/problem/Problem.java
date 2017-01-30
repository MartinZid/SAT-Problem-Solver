package solver.problem;

import java.util.ArrayList;

/**
 * Problem with clauses with AND (.).
 * @author Martin
 */
public class Problem {

    private ArrayList<Clause> clauses;   
    private final ArrayList<Integer> weights;
    
    public Problem(ArrayList<Integer> weights)
    {
        this.weights = weights;
        this.clauses = new ArrayList<>();
    }
    
    public void addClause(Clause c)
    {
        clauses.add(c);
    }
    
    /**
     * Return clauses true in proportion to all clauses.
     * E.g. 4 clauses true, 5 in total -> 0.8.
     * @param configuration
     * @return 
     */
    public double clausesTrue(ArrayList<Boolean> configuration)
    {
        int clausesTrue = 0;
        for(Clause c: clauses)
        {
            if(c.isTRUE(configuration))
                clausesTrue++;
        }
        return (double)clausesTrue/clauses.size();
    }
    
    /**
     * Are all clauses true?
     * @param configuration
     * @return 
     */
    public boolean isSat(ArrayList<Boolean> configuration)
    {
        return clausesTrue(configuration) == 1;
    }
    
    public int getN()
    {
        return weights.size();
    }
    
    public int getWeight(int i)
    {
        return weights.get(i);
    }
    
    @Override
    public String toString()
    {
        String problem = "";
        for(Clause c: clauses)
        {
            problem += "(" + c.toString() + ")";
        }
        return problem;
    }
}
