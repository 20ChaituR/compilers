package ast;

import codegen.Emitter;
import environment.Environment;

/**
 * The While class is a type of Statement that repeatedly
 * executes a given statement until the given condition
 * evaluates to false.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public class While extends Statement
{

    private Condition cond;
    private Statement stmt;

    /**
     * While constructor that consists of a condition
     * and a statement to be executed while that condition
     * is true.
     *
     * @param cond the while condition
     * @param stmt the statement to be executed
     */
    public While(Condition cond, Statement stmt)
    {
        this.cond = cond;
        this.stmt = stmt;
    }

    /**
     * This method executes the While statement by repeatedly executing
     * the statement within until the given Condition evaluates to false.
     *
     * @param env the environment for the variables
     */
    @Override
    public void exec(Environment env)
    {
        while (cond.eval(env))
        {
            stmt.exec(env);
        }
    }

    /**
     * This method compiles the While statement by first emitting
     * the beginWhile label, then compiling the Condition, then
     * compiling the Statement in the While, then emitting a jump
     * back to the start, then emitting the endWhile label.
     *
     * @param e the emitter for the generated code
     */
    @Override
    public void compile(Emitter e)
    {
        int labelID = e.nextLabelID();
        String beginLabel = "beginwhile" + labelID;
        String endLabel = "endwhile" + labelID;

        e.emit(beginLabel + ":");
        cond.compile(e, endLabel);
        stmt.compile(e);

        e.emit("# Jumps to " + beginLabel);
        e.emit("j " + beginLabel);
        e.emit(endLabel + ":");
        e.emit("");
    }
}
