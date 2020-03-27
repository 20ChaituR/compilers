package ast;
/*
 * Created by cravuri on 3/25/20
 */

import environment.Environment;

import java.util.List;

public class Block extends Statement
{

    private List<Statement> stmts;

    public Block(List<Statement> stmts)
    {
        this.stmts = stmts;
    }

    public List<Statement> getStmts()
    {
        return stmts;
    }

    @Override
    public void exec(Environment env)
    {
        for (Statement stmt : stmts)
        {
            stmt.exec(env);
        }
    }
}
