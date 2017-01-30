
import generator.Generator;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import solver.Result;
import solver.SATSolver;
import solver.parser.Parser;

/**
 *
 * @author Martin
 */
public class Main
{    
    /**
     * Writes intermediate results to file. These intermediate results can be 
     * plotted for simulated annealing parameters reconfiguration.
     * @param results 
     */
    private static void writeResultsToFile(ArrayList<Result> results)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("out.dat", false);
            for(Result r: results)
            {
                fos.write(r.toString().getBytes());
            }
            FileChannel fcOut = fos.getChannel();
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void printSATConfiguration(SATSolver solver)
    {
        for(Boolean b: solver.satConfig())
        {
            System.out.print((b? 1 : 0) + " ");
        } 
    }
    
    private static void solve()
    {
        int satCounter = 0;
        for(int i = 1; i <= 10; i++)
        {
            System.out.println(i);
            SATSolver solver = new SATSolver(500, 0.93, 1, 400);
            Parser parser = new Parser();
            try (Stream<String> stream = Files.lines(
                    Paths.get("./input/uf20-91(allSAT)/uf20-0" + i + ".cnf_w"))) {
                // for each line parse it
                stream.forEach(line -> parser.parseLine(line));
            } catch (IOException ex) {
                System.out.println("Input file error! " + ex);
            }
            
            int solution = solver.solve(parser.getProblem());
            if(solution != 0)
                satCounter++;
            
            System.out.println(solution);
            
            printSATConfiguration(solver);
            System.out.println("");
            //writeResultsToFile(solver.getResults());
        }
        System.out.println("SATs " + satCounter);
    }
    
    private static void generate() throws IOException
    {       
        for(int i = 1; i <= 20; i++)
        {
            Generator generator = new Generator();
            generator.generate("./input/uf50-218/uf50-0" + i + ".cnf");
        }
    }    
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException
    {
        //generate();
        solve();
    }
    
}
