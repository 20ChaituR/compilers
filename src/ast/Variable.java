package ast;

import codegen.Emitter;
import environment.Environment;

/**
 * The Variable class is a type of Expression that consists of
 * a variable name, pointing to a stored value in a given environment.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public class Variable extends Expression
{

    public String name;

    /**
     * Variable constructor that consists of
     * a String for the name of the variable.
     *
     * @param name the name of the variable
     */
    public Variable(String name)
    {
        this.name = name;
    }

    /**
     * This method gets the value of the given variable in
     * the given environment.
     *
     * @param env the environment for the variables
     * @return the value of the variable
     */
    @Override
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }

    @Override
    public void compile(Emitter e)
    {
        e.emit("# Puts " + name + " into v0");
        e.emit("la $t0 var" + name);
        e.emit("lw $v0 ($t0)\n");
    }
}
