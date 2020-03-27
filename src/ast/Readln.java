package ast;

import environment.Environment;

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
    private int val;

    /**
     * Readln constructor that consists of an id for
     * the variable name, and a value for the number
     * to be assigned to the variable
     *
     * @param id  the variable name
     * @param val the value of the variable
     */
    public Readln(String id, int val)
    {
        this.id = id;
        this.val = val;
    }

    /**
     * This method executes the Readln statement by assigning
     * the inputted value to the given variable.
     *
     * @param env the environment for the variables
     */
    @Override
    public void exec(Environment env)
    {
        env.setVariable(id, val);
    }
}
