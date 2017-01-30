package solver;

import java.util.ArrayList;
import solver.problem.Problem;

/**
 * One configuration in state space.
 * @author Martin
 */
class State {
    
    private final Problem problem;
    private final ArrayList<Boolean> configuration;
    private final int coef;
    
    State(Problem problem, int coef)
    {
        this.problem = problem;
        this.configuration = new ArrayList<>();
        this.coef = coef;
        for(int i = 0; i < problem.getN(); i++)
        {
            configuration.add(Boolean.FALSE);
        }
    }
    
    /**
     * Copy constructor.
     * @param state
     * @param problem 
     */
    State(State state, Problem problem)
    {
        this.problem = problem;
        this.configuration = new ArrayList<>();
        this.coef = state.coef;
        for(Boolean b: state.configuration)
        {
            configuration.add(b);
        }
    }

    /**
     * Is this state better than given state?
     * @param state
     * @return 
     */
    public boolean better(State state)
    {
        return weight() > state.weight();
    }    
    
    /** 
     * Return weight affected by advantage coefficient.
     * @return 
     */
    public int weight()
    {
        int weight = 0;
        for(int i = 0; i < configuration.size(); i++)
        {
            if(configuration.get(i))
                weight += problem.getWeight(i);
        }
        int result = 0;
        if(problem.clausesTrue(configuration) != 1)
            result = (int)(weight * Math.pow(problem.clausesTrue(configuration), coef));
        else
            result = weight * coef;
        return result;
    }   

    /**
     * Switch bit 0 <-> 1 on given position.
     * @param position 
     */
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

    public ArrayList<Boolean> getConfiguration()
    {
        return configuration;
    }
    
}
