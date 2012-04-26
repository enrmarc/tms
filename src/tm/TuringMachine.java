package tm;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class TuringMachine {

    private String currentState;
    private int cursor;
    private String input;
    private HashMap<String, Action> transitions;
    public  ArrayList<String> tape;
    private static String BLANK = "#";
    private boolean steps = false;
    private String FINAL_STATE = "qf";

    public TuringMachine(String input, HashMap transitions, boolean steps) {
        this.input = input;
        this.transitions = transitions;
        this.steps = steps;
        tape = new ArrayList<String>();
        init();
    }

    private void init() {
        tape.add(BLANK);
        for (int i = 0; i < input.length(); i++) {
            tape.add(String.valueOf(input.charAt(i)));
        }
        tape.add(BLANK);
        cursor = 1;
        currentState = "q1";
    }

    public void run() {
        while (true) {
            display();
            if (cursor == 0) {
                shiftRight();
                cursor = 1;
            }
            Action action;
            String key = currentState + tape.get(cursor);
            if ((action = transitions.get(key)) != null) {
                exec(action);
                if (currentState.equals(FINAL_STATE)) {
                    halt();
                    break;
                }
            } else {
                halt();
                break;
            }
            if (steps) {
                System.out.println("Press INTRO to continue...");
                new Scanner(System.in).nextLine();
            }
        }
    }

    private void shiftRight() {
        tape.add("#");
        for (int i = tape.size() - 1; i > 0; i--) {
            Collections.swap(tape, i, i -1);
        }
    }

    public void exec(Action action) {
        tape.set(cursor, action.newSymbol);
        currentState = action.nextState;

        cursor += action.move.equals("R") ? 1 : - 1;
    }


    public void halt() {
        if (currentState.equals(FINAL_STATE)) {
            System.out.println("Ok");
        } else {
            System.out.println("Error");
        }
    }

    public void display() {
        System.out.print(tape);
        System.out.println(" | State: " + currentState);
        for (int i = 0; i < tape.size(); i++) {
            System.out.print(" ");
            if (i == cursor) {
                System.out.print("*");
                break;
            }
            System.out.print("  ");
        }
        System.out.println();
    }
}

class Action {

    public String nextState;
    public String newSymbol;
    public String move;

    public Action(String nextState, String newSymbol, String move) {
        this.nextState = nextState;
        this.newSymbol = newSymbol;
        this.move = move;
    }
}

