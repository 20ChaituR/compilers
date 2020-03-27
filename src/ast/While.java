package ast;

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
}
