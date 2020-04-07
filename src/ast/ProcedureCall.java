package ast;

import environment.Environment;

import java.util.HashMap;
import java.util.List;

/**
 * The ProcedureCall class is a type of Expression that
 * represents a function that is being called with some
 * given arguments.
 */
public class ProcedureCall extends Expression
{

    private String id;
    private List<Expression> args;

    /**
     * ProcedureCall constructor that consists of a
     * String for the name of the procedure, and a
     * List of Expressions for each of the arguments.
     *
     * @param id   the name of the procedure
     * @param args the arguments of the procedure
     */
    public ProcedureCall(String id, List<Expression> args)
    {
        this.id = id;
        this.args = args;
    }

    /**
     * This method evaluates the ProcedureCall by creating
     * a new environment containing each of the parameters
     * and their associated arguments, declaring a new variable
     * that represents the return value, running the procedure's
     * inner statement in the new procedure environment.
     *
     * @param env the environment for the variables
     * @return the value of the return variable
     */
    @Override
    public int eval(Environment env)
    {
        ProcedureDeclaration decl = env.getProcedure(id);

        Environment procedureEnv = new Environment(new HashMap<>(), new HashMap<>(), env);
        List<String> params = decl.getParams();
        for (int i = 0; i < params.size(); i++)
        {
            procedureEnv.declareVariable(params.get(i), args.get(i).eval(env));
        }

        procedureEnv.declareVariable(id, 0);

        decl.getBody().exec(procedureEnv);
        return procedureEnv.getVariable(id);
    }
}
