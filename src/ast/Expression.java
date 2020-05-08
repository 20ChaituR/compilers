package ast;

import codegen.Emitter;
import environment.Environment;

/**
 * The Expression class in an abstract class which
 * represents any simple arithmetic expression, and
 * its value can be found by using the eval() method.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public abstract class Expression
{

    /**
     * This methods finds the value of the expression,
     * using the variables in the environment.
     *
     * @param env the environment for the variables
     * @return the value of the expression
     */
    public abstract int eval(Environment env);

    /**
     * This method compiles the expression, writing out
     * the MIPS code using given Emitter.
     *
     * @param e the emitter for the generated code
     */
    public abstract void compile(Emitter e);

}
