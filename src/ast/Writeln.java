package ast;

import codegen.Emitter;
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

    @Override
    public void compile(Emitter e)
    {
        exp.compile(e);

        e.emit("# Prints v0");
        e.emit("move $a0 $v0");
        e.emit("li $v0 1");
        e.emit("syscall\n");

        e.emit("# Prints newline");
        e.emit("la $a0 newline");
        e.emit("li $v0 4");
        e.emit("syscall\n");
    }
}
