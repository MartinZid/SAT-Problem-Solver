package solver;

/**
 *
 * @author Martin
 */
public class Result {

    private double t;
    private double w;
    
    public Result(double t, double w)
    {
        this.t = t;
        this.w = w;
    }

    @Override
    public String toString()
    {
        return t + "\t" + w + "\n";
    }
}
