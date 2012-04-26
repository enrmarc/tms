package tm;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {

    private Scanner scanner;
    private HashMap transitions;

    public Parser(File file) {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public HashMap parse() {
        transitions = new HashMap();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            parseLine(line);
        }
        return transitions;
    }

    public void parseLine(String line) {
        line = line.replaceAll("\\s", "");
        int i = line.indexOf("->");
        String keyState = line.substring(0, i);
        keyState = keyState.replaceAll(",", "");
        line = line.substring(i + 2, line.length());
        String[] array = line.split(",");
        Action action = new Action(array[0], array[1], array[2]);
        transitions.put(keyState, action);
    }
}
