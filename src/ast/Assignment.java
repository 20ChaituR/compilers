package ast;
/*
 * Created by cravuri on 3/25/20
 */

import environment.Environment;

public class Assignment extends Statement
{

    private String var;
    private Expression exp;

    public Assignment(String var, Expression exp)
    {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public void exec(Environment env)
    {
        env.setVariable(var, exp.eval(env));
    }
}
