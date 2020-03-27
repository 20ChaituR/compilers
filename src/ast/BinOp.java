package ast;

import environment.Environment;

/**
 * The BinOp class is a type of Expression that represents
 * a binary operator, consisting of an operation that is
 * applied to two expressions, such as summing or multiplying.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public class BinOp extends Expression
{

    private String op;
    private Expression exp1;
    private Expression exp2;

    /**
     * BinOp constructor that consists of a String
     * for the operator and two Expressions for the
     * two numbers that the operator will be applied to.
     *
     * @param op   the operator
     * @param exp1 the left side of the operator
     * @param exp2 the right side of the operator
     */
    public BinOp(String op, Expression exp1, Expression exp2)
    {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     * This method evaluates the BinOp by applying the given
     * operator to the values of the two given expressions.
     *
     * @param env the environment for the variables
     * @return the final value of the operator applied to the expressions
     */
    @Override
    public int eval(Environment env)
    {
        switch (op)
        {
            case "+":
                return exp1.eval(env) + exp2.eval(env);
            case "-":
                return exp1.eval(env) - exp2.eval(env);
            case "*":
                return exp1.eval(env) * exp2.eval(env);
            case "/":
                return exp1.eval(env) / exp2.eval(env);
            case "mod":
                return exp1.eval(env) % exp2.eval(env);
        }
        throw new IllegalArgumentException("Invalid binary operator");
    }
}
