package ast;

import environment.Environment;

/**
 * The Assignment class is a type of Statement that assigns
 * the value of an expression to the given variable.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public class Assignment extends Statement
{

    private String var;
    private Expression exp;

    /**
     * Assignment constructor that consists of a String
     * for the variable name, and an Expression for the
     * value.
     *
     * @param var the variable name
     * @param exp the value to be assigned
     */
    public Assignment(String var, Expression exp)
    {
        this.var = var;
        this.exp = exp;
    }

    /**
     * This method executes the Assignment statement by assigning
     * the value of the expression to the given variable in the
     * given environment.
     *
     * @param env the environment for the variable
     */
    @Override
    public void exec(Environment env)
    {
        env.setVariable(var, exp.eval(env));
    }
}
