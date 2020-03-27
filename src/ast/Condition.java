package ast;
/*
 * Created by cravuri on 3/25/20
 */

import environment.Environment;

public class Condition
{

    String relop;
    Expression exp1;
    Expression exp2;

    public Condition(String relop, Expression exp1, Expression exp2)
    {
        this.relop = relop;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public boolean eval(Environment env)
    {
        switch (relop)
        {
            case "=":
                return exp1.eval(env) == exp2.eval(env);
            case "<>":
                return exp1.eval(env) != exp2.eval(env);
            case "<":
                return exp1.eval(env) < exp2.eval(env);
            case ">":
                return exp1.eval(env) > exp2.eval(env);
            case "<=":
                return exp1.eval(env) <= exp2.eval(env);
            case ">=":
                return exp1.eval(env) >= exp2.eval(env);
        }

        throw new IllegalArgumentException("Invalid boolean operator");
    }
}
