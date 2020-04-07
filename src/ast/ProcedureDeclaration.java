package ast;

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
    public ProcedureDeclaration(String id, List<String> params, Statement stmt)
    {
        this.id = id;
        this.params = params;
        this.stmt = stmt;
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
}
