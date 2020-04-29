package ast;

import codegen.Emitter;
import environment.Environment;

/**
 * The If class is a type of Statement that executes
 * another given statement only if the given condition
 * evaluates to true.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public class If extends Statement
{

    private Condition cond;
    private Statement stmt;

    /**
     * If constructor that consists of a condition and
     * a statement to be executed if that condition is
     * true.
     *
     * @param cond the if condition
     * @param stmt the statement to be executed
     */
    public If(Condition cond, Statement stmt)
    {
        this.cond = cond;
        this.stmt = stmt;
    }

    /**
     * This method executes the statement within the
     * If statement only if the Condition evaluates
     * to true.
     *
     * @param env the environment for the variables
     */
    @Override
    public void exec(Environment env)
    {
        if (cond.eval(env))
        {
            stmt.exec(env);
        }
    }

    /**
     * This method compiles the If statement by
     * compiling the condition, then the statement
     * within the If, then emitting the end label.
     *
     * @param e the emitter for the generated code
     */
    @Override
    public void compile(Emitter e)
    {
        int labelID = e.nextLabelID();
        String beginLabel = "beginif" + labelID;
        String endLabel = "endif" + labelID;

        e.emit(beginLabel + ":");
        cond.compile(e, endLabel);
        stmt.compile(e);
        e.emit(endLabel + ":");
        e.emit("");
    }
}
