package ast;

import environment.Environment;

import java.util.List;

/**
 * The Program class is a type of Statement
 * that encapsulates an entire program, consisting
 * of a list of procedures and a final statement
 * to be run.
 *
 * @author Chaitanya Ravuri
 * @version April 7, 2020
 */
public class Program extends Statement
{

    private List<ProcedureDeclaration> procedures;
    private Statement stmt;

    /**
     * Program constructor that consists of a
     * List of ProcedureDeclarations for the
     * procedures and a Statement for the final
     * statement to be run.
     *
     * @param procedures the declarations at the start
     * @param stmt       the statement to be run
     */
    public Program(List<ProcedureDeclaration> procedures, Statement stmt)
    {
        this.procedures = procedures;
        this.stmt = stmt;
    }

    /**
     * Adds a given ProcedureDeclaration to the
     * list of procedures
     *
     * @param proc the procedure to be added
     */
    public void addProcedure(ProcedureDeclaration proc)
    {
        procedures.add(proc);
    }

    /**
     * This method executes the Program by declaring
     * the procedures, then executing the final Statement
     *
     * @param env the environment for the variables
     */
    @Override
    public void exec(Environment env)
    {
        for (ProcedureDeclaration proc : procedures)
        {
            proc.exec(env);
        }
        stmt.exec(env);
    }
}
