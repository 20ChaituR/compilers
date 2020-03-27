package ast;
/*
 * Created by cravuri on 3/25/20
 */

import environment.Environment;

public class If extends Statement
{

    private Condition cond;
    private Statement stmt;

    public If(Condition cond, Statement stmt)
    {
        this.cond = cond;
        this.stmt = stmt;
    }

    @Override
    public void exec(Environment env)
    {
        if (cond.eval(env))
        {
            stmt.exec(env);
        }
    }
}
