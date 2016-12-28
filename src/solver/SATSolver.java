package solver;

import java.util.concurrent.ThreadLocalRandom;
import solver.problem.Problem;

/**
 *
 * @author Martin
 */
public class SATSolver {

    /*                  PARAMETRS                       */
    private final int startT;       // starting temperature
    private final double a;         //cooling coeficient
    private final double finalT;    // temperature of frozen
    private final int equilibrium;  // equilibrium coeficient
    /* ------------------------------------------------ */
    
    private State bestSolution;
    private State state;
    private double temperature;    
    private Problem problem;
    
    public SATSolver(int startT, double a, double finalT, 
            int equilibrium)
    {
        this.temperature = startT;
        this.startT = startT;
        this.a = a;
        this.finalT = finalT;
        this.equilibrium = equilibrium;
    }

    /**
     * Solve knapsack problem by simulated annealing.
     * @param problem
     * @return 
     */
    public int solve(Problem problem)
    {
        this.problem = problem;
        bestSolution = new State(problem);
        state = new State(problem);
        
        while(!frozen())
        {
            // stay on this temperature for a while (equilibrium)
            for(int i = 0; equilibrium(i); i++)
            {
                System.out.println(temperature + "\t" + state.weight());
                // get (new) state
                state = tryState();
                // found better solution? Save it!
                if(state.better(bestSolution))
                    bestSolution = state;
            }
            temperature = cool();
        }
        System.out.println(bestSolution.weight());
        return bestSolution.weight();        
        
    }

    private State tryState()
    {
        int position;
        State newState;
        do {
            position = ThreadLocalRandom.current().nextInt(0, problem.getN());
            newState = new State(state, problem);
            // get new configuration (switch one bit)
            newState.toggleBit(position);
            System.out.println(newState.isSAT());
        } while(!newState.isSAT());
        
        if(newState.better(state))
            return newState;
        
        // newState still got change even if it is worse
        int delta = newState.weight()-state.weight();
        double x = (float)Math.random();
        if(x < Math.pow(Math.E, ((double)delta/temperature)))
            return newState;
        return state;        
        
    }    
    
    private boolean frozen()
    {
        return temperature <= finalT;
    }
    
    private boolean equilibrium(int i)
    {
        return i < (problem.getN() * equilibrium);
    }
    
    private double cool()
    {
        return temperature * a;
    }    
}
