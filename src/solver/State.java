package solver;

import java.util.ArrayList;
import solver.problem.Problem;

/**
 *
 * @author Martin
 */
class State {
    
    private final Problem problem;
    private final ArrayList<Boolean> configuration;
    
    State(Problem problem)
    {
        this.problem = problem;
        this.configuration = new ArrayList<>();
        for(int i = 0; i < problem.getN(); i++)
        {
            configuration.add(Boolean.FALSE);
        }
    }
    
    State(State state, Problem problem)
    {
        this.problem = problem;
        this.configuration = new ArrayList<>();
        for(Boolean b: state.configuration)
        {
            configuration.add(b);
        }
    }

    public boolean better(State state)
    {
        double clausesTrueThis = problem.clausesTrue(configuration);
        double clausesTrueState = state.problem.clausesTrue(configuration);
        if(clausesTrueThis != clausesTrueState)
            return clausesTrueThis > clausesTrueState;
        return (weight() > state.weight());
    }

    public int weight()
    {
        int weight = 0;
        for(int i = 0; i < configuration.size(); i++)
        {
            if(configuration.get(i))
                weight += problem.getWeight(i);
        }
        return weight;
    }
//    public boolean better(State state)
//    {
//        return weight() > state.weight();
//    }    
//    
//    public int weight()
//    {
//        int weight = 0;
//        for(int i = 0; i < configuration.size(); i++)
//        {
//            if(configuration.get(i))
//                weight += problem.getWeight(i);
//        }
//        double clausesTrue = problem.clausesTrue(configuration) == 1?
//                2: problem.clausesTrue(configuration);
//        return (int)(weight*clausesTrue);
//    }    

    public void toggleBit(int position)
    {
        configuration.set(position, !configuration.get(position));
    }
    
    public boolean isSAT()
    {
        return problem.isSat(configuration);
    }

    public double clausesTrue()
    {
        return problem.clausesTrue(configuration);
    }
    
}
