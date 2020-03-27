package ast;
/*
 * Created by cravuri on 3/25/20
 */

import environment.Environment;

public class BinOp extends Expression
{

    private String op;
    private Expression exp1;
    private Expression exp2;


    public BinOp(String op, Expression exp1, Expression exp2)
    {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public int eval(Environment env)
    {
        switch (op)
        {
            case "+":
                return exp1.eval(env) + exp2.eval(env);
            case "-":
                return exp1.eval(env) - exp2.eval(env);
            case "*":
                return exp1.eval(env) * exp2.eval(env);
            case "/":
                return exp1.eval(env) / exp2.eval(env);
            case "mod":
                return exp1.eval(env) % exp2.eval(env);
        }
        throw new IllegalArgumentException("Invalid binary operator");
    }
}
