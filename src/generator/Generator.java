package generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * Generates instance for SAT weighted problem from DIMACS file format.
 * @author Martin
 */
public class Generator {

    private boolean eof = false;
    private int clauseNumber = 0; 
    
    /**
     * Reads DIMACS file (filePath) by lines.
     * @param filePath 
     * @throws java.io.IOException 
     */
    public void generate(String filePath) throws IOException
    {
        Path path = Paths.get(filePath + "_w");
        try{
            Files.createFile(Paths.get(filePath+ "_w"));
        } catch (IOException ex) {
            Files.write(path, ("").getBytes());
            System.out.println("File " + filePath + "_w already existed. Proceeding...");
        }
        try (Stream<String> stream = Files.lines(
                Paths.get(filePath))) {
            // for each line run solve given problem
            stream.forEach(line ->
            {
                try {
                    parseLine(line, path);
                } catch (IOException ex)
                {
                    System.out.println("Output file error! " + ex);
                }
            });
        } catch (IOException ex) {
            System.out.println("Input file error! " + ex);
        }
    }

    /**
     * Parses line a writes to output file.
     * @param line
     * @param path
     * @throws IOException 
     */
    private void parseLine(String line, Path path) throws IOException
    {
        if(eof)
            return;
        String [] data = line.split(" ");
        if(data[0].equals("p")) //Metadata
        {
            parseMetadata(line, path);
            return;
        }
        if(data[0].equals("c")) // Comment
            return;
        if(data[0].equals("%")) // EOF
        {
            eof = true;
            return;
        }
        try { //write line to output file
            Files.write(path, line.trim().concat("\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex)
        {
            System.out.println("Output file error! " + ex);
        }
    }

    /**
     * Get clauses count and generates random weight for each literal.
     * @param line
     * @param path
     * @throws IOException 
     */
    private void parseMetadata(String line, Path path) throws IOException
    {
        String[] data = line.split(" ");
        clauseNumber = Integer.parseInt(data[2]);
        Files.write(path, line.concat("\n").getBytes(), StandardOpenOption.APPEND);
        //generate random weights
        for(int i = 0; i < clauseNumber; i++)
        {
            int randomPrice = ThreadLocalRandom.current().nextInt(1, 100 + 1);
            Files.write(path, Integer.toString(randomPrice).concat(" ").getBytes(),
                    StandardOpenOption.APPEND);
        }
        Files.write(path, ("\n").getBytes(), StandardOpenOption.APPEND);
    }
    
}
