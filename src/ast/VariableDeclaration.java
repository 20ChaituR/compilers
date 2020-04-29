package ast;
/*
 * Created by cravuri on 4/29/20
 */

import codegen.Emitter;
import environment.Environment;

public class VariableDeclaration extends Statement
{

    private String id;
    private Program p;

    public VariableDeclaration(String id, Program p)
    {
        this.id = id;
        this.p = p;
    }

    public String getID()
    {
        return id;
    }

    @Override
    public void exec(Environment env)
    {
        env.declareVariable(id, 0);
    }

    @Override
    public void compile(Emitter e)
    {
        p.addVariable(this);
    }
}
