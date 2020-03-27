package ast;

import environment.Environment;

import java.util.List;

/**
 * The Block class is a type of Statement that
 * consists of a block of multiple other statements.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public class Block extends Statement
{

    private List<Statement> stmts;

    /**
     * Block constructor that consists of the list
     * of statements contained within the block.
     *
     * @param stmts the statements in the block
     */
    public Block(List<Statement> stmts)
    {
        this.stmts = stmts;
    }

    /**
     * Gets the list of statements in the block
     *
     * @return the list of statements
     */
    public List<Statement> getStmts()
    {
        return stmts;
    }

    /**
     * This method executes the Block statement by looping
     * through each individual statement in the list of
     * statements and executing them in order.
     *
     * @param env the environment for the variables
     */
    @Override
    public void exec(Environment env)
    {
        for (Statement stmt : stmts)
        {
            stmt.exec(env);
        }
    }
}
