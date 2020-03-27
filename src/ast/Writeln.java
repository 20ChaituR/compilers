package ast;
/*
 * Created by cravuri on 3/25/20
 */

import environment.Environment;

public class Writeln extends Statement
{

    private Expression exp;

    public Writeln(Expression exp)
    {
        this.exp = exp;
    }

    @Override
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
}
