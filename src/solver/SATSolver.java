package solver;

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
    
    private int id;
    
    //private State bestSolution;
    private double temperature;    
    
    public SATSolver(int startT, double a, double finalT, 
            int equilibrium)
    {
        this.temperature = startT;
        this.startT = startT;
        this.a = a;
        this.finalT = finalT;
        this.equilibrium = equilibrium;
    }

    public void solve()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
