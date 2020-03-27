package ast;

import environment.Environment;

/**
 * The Condition class is a special AST element that consists
 * two expressions and a relop, and either evaluates to true
 * or false.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public class Condition
{

    String relop;
    Expression exp1;
    Expression exp2;

    /**
     * Condition constructor that consists of the relop,
     * and the two expressions which are to be compared by
     * the relop.
     *
     * @param relop the operator comparing the two expressions
     * @param exp1  the left side of the relop
     * @param exp2  the right side of the relop
     */
    public Condition(String relop, Expression exp1, Expression exp2)
    {
        this.relop = relop;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     * This method evaluates the Condition comparing the
     * values of the two expressions using the given relop.
     *
     * @param env the environment for the variables
     * @return either true or false depending on the comparison between both expressions
     */
    public boolean eval(Environment env)
    {
        switch (relop)
        {
            case "=":
                return exp1.eval(env) == exp2.eval(env);
            case "<>":
                return exp1.eval(env) != exp2.eval(env);
            case "<":
                return exp1.eval(env) < exp2.eval(env);
            case ">":
                return exp1.eval(env) > exp2.eval(env);
            case "<=":
                return exp1.eval(env) <= exp2.eval(env);
            case ">=":
                return exp1.eval(env) >= exp2.eval(env);
        }

        throw new IllegalArgumentException("Invalid boolean operator");
    }
}
