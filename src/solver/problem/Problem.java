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
