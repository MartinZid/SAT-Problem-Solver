package solver;

/**
 * Secondary class for results checking.
 * @author Martin
 */
public class Result {

    private final double t;
    private final double w;
    
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
