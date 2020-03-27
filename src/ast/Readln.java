package ast;
/*
 * Created by cravuri on 3/25/20
 */

import environment.Environment;

public class Readln extends Statement
{

    private String id;
    private int val;

    public Readln(String id, int val)
    {
        this.id = id;
        this.val = val;
    }

    @Override
    public void exec(Environment env)
    {
        env.setVariable(id, val);
    }
}
