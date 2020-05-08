package ast;

import codegen.Emitter;
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

    /**
     * This method compiles the Assignment statement by first
     * compiling the expression, then storing the value of that
     * expression in the variable.
     *
     * @param e the emitter for the generated code
     */
    @Override
    public void compile(Emitter e)
    {
        exp.compile(e);
        e.emit("# Assigns v0 to " + var);
        if (e.isLocalVariable(var))
        {
            int offset = e.getOffset(var);
            e.emit("sw $v0 " + offset + "($sp)\n");
        }
        else
        {
            e.emit("la $t0 var" + var);
            e.emit("sw $v0 ($t0)\n");
        }
    }
}
