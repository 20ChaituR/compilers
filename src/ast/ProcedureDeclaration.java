package ast;

import codegen.Emitter;
import environment.Environment;

import java.util.List;

/**
 * The ProcedureDeclaration class is a type of Statement
 * that declares a procedure at the start of the program.
 *
 * @author Chaitanya Ravuri
 * @version April 7, 2020
 */
public class ProcedureDeclaration extends Statement
{

    private String id;
    private List<String> params;
    private List<String> localVars;
    private Statement stmt;

    /**
     * ProcedureDeclaration constructor that consists of a
     * String for the name of the procedure, a List of
     * parameters, and a Statement to execute within the
     * procedure.
     *
     * @param id     the name of the procedure
     * @param params the parameters of the procedure
     * @param stmt   the statement within the procedure
     */
    public ProcedureDeclaration(String id, List<String> params, List<String> localVars, Statement stmt)
    {
        this.id = id;
        this.params = params;
        this.localVars = localVars;
        this.stmt = stmt;
    }

    /**
     * Gets the procedure's id
     *
     * @return id
     */
    public String getID()
    {
        return id;
    }

    /**
     * Gets the statement within the procedure
     *
     * @return stmt
     */
    public Statement getBody()
    {
        return stmt;
    }

    /**
     * Gets the parameters of the procedure
     *
     * @return params
     */
    public List<String> getParams()
    {
        return params;
    }

    /**
     * Gets the local vars of the procedure
     *
     * @return params
     */
    public List<String> getLocalVars()
    {
        return localVars;
    }

    /**
     * This method executes the ProcedureDeclaration by
     * assigning the declaration to the procedure name
     * in the environment.
     *
     * @param env the environment for the variables
     */
    @Override
    public void exec(Environment env)
    {
        env.setProcedure(id, this);
    }

    @Override
    public void compile(Emitter e)
    {
        e.emit("proc" + id + ":");

        for (String var : localVars)
        {
            e.emitPush("$zero");
        }

        e.setProcedureContext(this);
        stmt.compile(e);
        e.clearProcedureContext();

        for (String var : localVars)
        {
            e.emitPop("$t0");
        }

        e.emit("# Jumps back to procedure call");
        e.emit("jr $ra\n");
    }
}
