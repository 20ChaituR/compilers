package ast;

import environment.Environment;

/**
 * The Number class is a type of Expression that
 * consists of a single integer.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public class Number extends Expression
{

    public int value;

    /**
     * Number constructor that consists of an
     * integer, the value of the number
     *
     * @param value the value of the number
     */
    public Number(int value)
    {
        this.value = value;
    }

    /**
     * This method returns the value of the number
     *
     * @param env the environment for the variables
     * @return the value of the number
     */
    @Override
    public int eval(Environment env)
    {
        return value;
    }
}
