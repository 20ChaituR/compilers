package ast;

import codegen.Emitter;
import environment.Environment;

import java.util.Scanner;

/**
 * The Readln class is a type of Statement that takes the
 * user's input from the console and assigns that value to a
 * given variable.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public class Readln extends Statement
{

    private String id;
    private Scanner consoleIn;

    /**
     * Readln constructor that consists of an id for
     * the variable name, and Scanner for the console
     * input
     *
     * @param id        the variable name
     * @param consoleIn the console input scanner
     */
    public Readln(String id, Scanner consoleIn)
    {
        this.id = id;
        this.consoleIn = consoleIn;
    }

    /**
     * This method executes the Readln statement by
     * assigning the inputted value to the given variable.
     *
     * @param env the environment for the variables
     */
    @Override
    public void exec(Environment env)
    {
        int val = consoleIn.nextInt();
        env.setVariable(id, val);
    }

    /**
     * This method compiles the Readln statement by
     * reading the user input and assigning it into
     * the given variable.
     *
     * @param e the emitter for the generated code
     */
    @Override
    public void compile(Emitter e)
    {
        e.emit("# Puts user input into v0");
        e.emit("li $v0 5");
        e.emit("syscall\n");
        e.emit("# Assigns v0 to " + id);
        e.emit("la $t0 var" + id);
        e.emit("sw $v0 ($t0)\n");
    }
}
