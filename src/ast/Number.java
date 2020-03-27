package ast;
/*
 * Created by cravuri on 3/25/20
 */

import environment.Environment;

public class Number extends Expression
{

    public int value;

    public Number(int value)
    {
        this.value = value;
    }

    @Override
    public int eval(Environment env)
    {
        return value;
    }
}
