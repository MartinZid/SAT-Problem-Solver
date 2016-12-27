
import generator.Generator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import solver.SATSolver;
import solver.parser.Parser;

/**
 *
 * @author Martin
 */
public class Main
{

    private static void generate() throws IOException
    {
        Generator generator = new Generator();
        generator.generate("./input/uf20-91(allSAT)/uf20-01.cnf"); 
    }
    
    private static void solve()
    {
        Parser parser = new Parser();
        SATSolver solver = new SATSolver(100, 0.92, 30, 30);
        try (Stream<String> stream = Files.lines(
                Paths.get("./input/uf20-91(allSAT)/uf20-01.cnf_w"))) {
            // for each line parse it
            stream.forEach(line -> parser.parseLine(line));
        } catch (IOException ex) {
            System.out.println("Input file error! " + ex);
        }
        System.out.println(parser.getProblem().toString());
        solver.solve();
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
