package ast;

import environment.Environment;

/**
 * The Writeln class is a type of Statement that prints
 * out the value of the given expression.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public class Writeln extends Statement
{

    private Expression exp;

    /**
     * Writeln constructor that consists of an
     * expression to be printed out to console
     *
     * @param exp the expression to be printed out
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }

    /**
     * This method executes the Writeln statement by
     * printing the value of the given expression.
     *
     * @param env the environment for the variables
     */
    @Override
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
}
