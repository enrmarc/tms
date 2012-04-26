package tm;

import java.io.File;
import java.util.HashMap;

public class Simulator {
    public static void main(String[] args) {
        boolean steps = false;
        if (args.length < 1 || args.length > 2) {
            System.err.println("./tm input_file [s]");
            System.err.printlb("[s] for execution step by step.");
            System.exit(1);
        }
        
        if (args.length == 2) {
            if (args[1].equals("s")) {
                steps = true;
            }
        }

        Parser parser = new Parser(new File(args[0]));
        String input = parser.getInput();
        HashMap transitions = parser.parse();

        new TuringMachine(input, transitions, steps).run();
    }
}

