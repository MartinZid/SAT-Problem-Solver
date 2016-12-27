package solver.problem;

import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class Problem {

    private ArrayList<Clause> clauses = new ArrayList<>();
    
    public void addClause(Clause c)
    {
        clauses.add(c);
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
