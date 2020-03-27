package ast;
/*
 * Created by cravuri on 3/25/20
 */

import environment.Environment;

public class Variable extends Expression
{

    public String name;

    public Variable(String name)
    {
        this.name = name;
    }

    @Override
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }
}
